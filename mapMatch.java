import java.util.*;

public class mapMatch {

    //Score to determine an acceptabe match
    private static double validMatchScore = 0.6;

    public lineMapResult map(List<String> oldLines, List<String> newLines){
        
        //Find exact matches
        List<LinePair> exactMatches = findExactMatch(oldLines, newLines);

        //Convert exact matches list to a map
        Map<Integer, Integer> exactMatchMapping = new HashMap<>();
        for (LinePair p: exactMatches){

            exactMatchMapping.put(p.oldIndex, p.newIndex);

        }

        //Find matches based on similarity
        List<CandidateMatch> candidateMatches = findCandidates(oldLines, newLines, exactMatchMapping);
        
        //Resolve Conflicts
        Map<Integer, Integer> finalMap = ConflictResolve.resolveConflicts(candidateMatches, exactMatchMapping);
        
        for (int i = 0; i < oldLines.size(); i++) {
            if (!finalMap.containsKey(i)) {
                finalMap.put(i, -2);   //any old line that didnt match mapped to negative 1
            }
        }

        //Return Final Result
        return new lineMapResult(finalMap);
    }

    //LinePair Class
    private static class LinePair {
        
        int oldIndex;
        int newIndex;

        //Constructor
        public LinePair(int oldIndex, int newIndex) {

            this.oldIndex = oldIndex;
            this.newIndex = newIndex;
        } 
    }

    private List<LinePair> findExactMatch(List<String> oldLines, List<String> newLines){
        List<LinePair> exactMatches = new ArrayList<>(); 
        int lastNewIndex = -1; //tracks last matched

        for(int i = 0; i < oldLines.size(); i++){ //loop throguh old lines
            String oldText = oldLines.get(i); 

            for(int j = lastNewIndex + 1; j < newLines.size(); j++){ //loop through new lines
                String newText = newLines.get(j); 

                if(oldText.equals(newText)){ //if match we have to record
                    exactMatches.add(new LinePair(i, j)); 
                    lastNewIndex = j; //update the last index used so matches come after this
                    break;//stop scanning and move on
                }
            }
        }

        return exactMatches;
    }

    //Helper class to store match info
    private static class CandidateMatch{
        int oldIndex;
        int newIndex;
        double score;

        CandidateMatch(int oldIndex, int newIndex, double score){
            this.oldIndex =oldIndex;
            this.newIndex = newIndex;
            this.score = score;
        }

    }

    //Helper Class to calculate content and context similarity
    private static class Similarity{

        //Content Similarity
        public double contentSimilarity(String s1, String s2){

            int distance = levenshteinDistance(s1, s2);

            //Handle empty string case
            int l1 = s1.length();
            int l2 = s2.length();
            int max = (l1 > l2) ? l1 : l2;

            if (max == 0){

                return 1.0;

            }

            //Return content similarity score
            return 1.0 - ((double) distance / max);


        }

        //Context Similarity
        public double contextSimilarity(int oldIndex, int newIndex, Map<Integer, Integer> exactMatchMap){

            //No exact matches
            if(exactMatchMap.isEmpty()){

                return 0.0;

            }

            //Initialize difference as max
            int difference = Integer.MAX_VALUE;


            //Iterate through exact matches
            for(Map.Entry<Integer, Integer> e : exactMatchMap.entrySet()){

                //Exact match indexes
                int emOldIndex = e.getKey();
                int emNewIndex = e.getValue();


                //Absolute Value Differences
                int diffOld = (oldIndex > emOldIndex) ? (oldIndex - emOldIndex) : (emOldIndex - oldIndex);
                int diffNew = (newIndex > emNewIndex) ? (newIndex - emNewIndex) : (emNewIndex - newIndex);
                

                //Set new lowest difference
                int tempDiff = diffOld + diffNew;

                if(tempDiff < difference){

                    difference = tempDiff;

                }

            }

            double similarity = (1.0 / (1.0 + difference)) * 10;

            //Cap similarity at 1.0
            return (similarity > 1.0) ? 1.0 : similarity;

        }

        //Levenshtein Algorithm
        private static int levenshteinDistance(String s1, String s2){

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for(int i = 0; i <= s1.length(); i++){

                for(int j = 0; j <= s2.length(); j++){

                    if(i == 0){

                        dp[i][j] = j;

                    }else if(j == 0) {
                        
                        dp[i][j] = i;

                    }else{

                        dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(s1.charAt(i - 1), s2.charAt(j - 1)), dp[i - 1][j] + 1, dp[i][j - 1] + 1);

                    }

                }


            }

            return dp[s1.length()][s2.length()];

        }

       //Helper methods for levenshtein
        public static int costOfSubstitution(char a, char b){

            return a == b ? 0 : 1;

        }

        public static int min(int... numbers){

            return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);

        }


    }

    private List<CandidateMatch> findCandidates(List<String> oldLines,List<String> newLines, Map<Integer, Integer> exactMatchesMap) {

    Similarity similarity = new Similarity();
    List<CandidateMatch> candidates = new ArrayList<>();

    int nOld = oldLines.size();
    int nNew = newLines.size();

    for (int i = 0; i < nOld; i++) {

        // skip if old line already has an exact match
        if (exactMatchesMap.containsKey(i)) {
            continue;
        }

        String oldLine = oldLines.get(i);

        CandidateMatch bestCandidate = null;

        for (int j = 0; j < nNew; j++) {

            if (exactMatchesMap.containsValue(j)) {
                continue;
            }

            String newLine = newLines.get(j);
            double bestContentScore = similarity.contentSimilarity(oldLine, newLine);

            if (j + 1 < nNew && !exactMatchesMap.containsValue(j + 1)) {
                String combined = newLine + " " + newLines.get(j + 1);
                double splitScore = similarity.contentSimilarity(oldLine, combined);
                if (splitScore > bestContentScore) {
                    bestContentScore = splitScore;
                }
            }

            if (bestContentScore < 0.3) {
                continue;
            }

            double contextScore = similarity.contextSimilarity(i, j, exactMatchesMap);

            double totalScore = 0.7 * bestContentScore + 0.3 * contextScore;

            if (bestCandidate == null || totalScore > bestCandidate.score) {
                bestCandidate = new CandidateMatch(i, j, totalScore);
            }
        }

        if (bestCandidate != null && bestCandidate.score >= validMatchScore) {
            candidates.add(bestCandidate);
        }
    }

    candidates.sort((a, b) -> Double.compare(b.score, a.score));
    return candidates;
}

    private static class ConflictResolve {

        static Map<Integer, Integer> resolveConflicts(List<CandidateMatch> candidateMatches, Map<Integer, Integer> exactMatchMapping){

            Map<Integer, Integer> result = new HashMap<>(exactMatchMapping);

            //Lists to store already matched lines
            List<Integer> oldMatchedLines = new ArrayList<>(exactMatchMapping.keySet());
            List<Integer> newMatchedLines = new ArrayList<>(exactMatchMapping.values());

            for(CandidateMatch c : candidateMatches){

                //Skip bad matches
                if (c.score < validMatchScore) {
                    
                    continue;

                }


                //Skip already matched lines
                if(oldMatchedLines.contains(c.oldIndex)){

                    continue;

                }

                if(newMatchedLines.contains(c.newIndex)){

                    continue;

                }

                //Add unused pair to matches
                result.put(c.oldIndex, c.newIndex);
                
                //Add match indexes to used line lists
                oldMatchedLines.add(c.oldIndex);
                newMatchedLines.add(c.newIndex);

            }

            //Return the final mapping
            return result;

        }

    }

}