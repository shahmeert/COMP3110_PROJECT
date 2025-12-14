import java.util.*;

public class mapMatch {

    // Score to determine an acceptable match
    private static double validMatchScore = 0.6;

    public lineMapResult map(List<String> oldLines, List<String> newLines){

        // Find exact matches
        List<LinePair> exactMatches = findExactMatch(oldLines, newLines);

        // Convert exact matches list to a map
        Map<Integer, Integer> exactMatchMapping = new HashMap<>();
        for (LinePair p : exactMatches){
            exactMatchMapping.put(p.oldIndex, p.newIndex);
        }

        // Find matches based on similarity
        List<CandidateMatch> candidateMatches =
                findCandidates(oldLines, newLines, exactMatchMapping);

        // Resolve conflicts
        Map<Integer, Integer> finalMap =
                ConflictResolve.resolveConflicts(candidateMatches, exactMatchMapping);

        // Mark any old lines that didn't match at all
        for (int i = 0; i < oldLines.size(); i++) {
            if (!finalMap.containsKey(i)) {
                finalMap.put(i, -1);   // use -1 consistently for "no mapping"
            }
        }

        // Return Final Result
        return new lineMapResult(finalMap);
    }

    // LinePair Class
    private static class LinePair {
        int oldIndex;
        int newIndex;

        public LinePair(int oldIndex, int newIndex) {
            this.oldIndex = oldIndex;
            this.newIndex = newIndex;
        }
    }

    private List<LinePair> findExactMatch(List<String> oldLines, List<String> newLines){
        List<LinePair> exactMatches = new ArrayList<>();
        int lastNewIndex = -1; // tracks last matched

        for (int i = 0; i < oldLines.size(); i++){ // loop through old lines
            String oldText = oldLines.get(i);

            if (oldText.trim().isEmpty()) {
                continue;
            }

            for (int j = lastNewIndex + 1; j < newLines.size(); j++){ // loop through new lines
                String newText = newLines.get(j);

                // also skip blank lines in the new file as exact matches
                if (newText.trim().isEmpty()) {
                    continue;
                }

                if (oldText.equals(newText)){ // exact match
                    exactMatches.add(new LinePair(i, j));
                    lastNewIndex = j; // update last index used so matches come after this
                    break;           
                }
            }
        }

        return exactMatches;
    }

    // Helper class to store match info
    private static class CandidateMatch{
        int oldIndex;
        int newIndex;
        double score;

        CandidateMatch(int oldIndex, int newIndex, double score){
            this.oldIndex = oldIndex;
            this.newIndex = newIndex;
            this.score = score;
        }
    }

    // Helper Class to calculate content and context similarity
    private static class Similarity{

        // Content Similarity
        public double contentSimilarity(String s1, String s2){

            s1 = s1.trim();
            s2 = s2.trim();

            int l1 = s1.length();
            int l2 = s2.length();
            int max = (l1 > l2) ? l1 : l2;

            // both lines blank -> don't treat as strong match
            if (max == 0){
                return 0.0;
            }

            int distance = levenshteinDistance(s1, s2);
            double base = 1.0 - ((double) distance / max);

            // Very short lines (like "{" or "}") should not be strong matches
            if (max <= 3) {
                base *= 0.4;   // dampen similarity for tiny lines
            }

            return base;
        }

        // Context Similarity
        public double contextSimilarity(int oldIndex, int newIndex, Map<Integer, Integer> exactMatchMap){

            // No exact matches
            if (exactMatchMap.isEmpty()){
                return 0.0;
            }

            int difference = Integer.MAX_VALUE;

            // Iterate through exact matches
            for(Map.Entry<Integer, Integer> e : exactMatchMap.entrySet()){

                int emOldIndex = e.getKey();
                int emNewIndex = e.getValue();

                int diffOld = Math.abs(oldIndex - emOldIndex);
                int diffNew = Math.abs(newIndex - emNewIndex);

                int tempDiff = diffOld + diffNew;

                if (tempDiff < difference){
                    difference = tempDiff;
                }
            }

            double similarity = (1.0 / (1.0 + difference)) * 10;

            // Cap similarity at 1.0
            return (similarity > 1.0) ? 1.0 : similarity;
        }

        // Levenshtein Algorithm
        private static int levenshteinDistance(String s1, String s2){

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for(int i = 0; i <= s1.length(); i++){
                for(int j = 0; j <= s2.length(); j++){
                    if(i == 0){
                        dp[i][j] = j;
                    } else if(j == 0) {
                        dp[i][j] = i;
                    } else{
                        dp[i][j] = min(
                                dp[i - 1][j - 1] + costOfSubstitution(s1.charAt(i - 1), s2.charAt(j - 1)),
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1
                        );
                    }
                }
            }

            return dp[s1.length()][s2.length()];
        }

        public static int costOfSubstitution(char a, char b){
            return a == b ? 0 : 1;
        }

        public static int min(int... numbers){
            return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
        }

    }

    // Finds possible matches for lines that were NOT exact matches
    private List<CandidateMatch> findCandidates(List<String> oldLines, List<String> newLines, Map<Integer, Integer> exactMatchesMap){

        Similarity similarity = new Similarity();
        List<CandidateMatch> candidates = new ArrayList<>();  // Holds all possible match pairs

        int nOld = oldLines.size();
        int nNew = newLines.size();

        // Loop through all old lines
        for (int i = 0; i < nOld; i++){

            // Skip if this line already had an exact match
            if (exactMatchesMap.containsKey(i)){
                continue;
            }

            String oldLine = oldLines.get(i);  // The file line we’re trying to match

            // try matching this line to every new line
            for (int j = 0; j < nNew; j++) {

                // Skip if the line is already part of an exact match
                if (exactMatchesMap.containsValue(j)){
                    continue;
                }

                String newLine = newLines.get(j);  // A candidate new file line to compare

                double bestContentScore = similarity.contentSimilarity(oldLine, newLine);

                // Check splits: try comparing oldLine with new[j] + new[j+1] combined
                if (j + 1 < nNew && !exactMatchesMap.containsValue(j + 1)){
                    String combined = newLine + " " + newLines.get(j + 1);
                    double splitScore = similarity.contentSimilarity(oldLine, combined);

                    if (splitScore > bestContentScore){
                        bestContentScore = splitScore;
                    }
                }

                // skip if not similar enough
                if (bestContentScore < 0.3){
                    continue;
                }

                double contextScore = similarity.contextSimilarity(i, j, exactMatchesMap);

                double totalScore = 0.7 * bestContentScore + 0.3 * contextScore;

                if (totalScore >= validMatchScore){
                    candidates.add(new CandidateMatch(i, j, totalScore));
                }
            }
        }

        // Sort from best score to worst score
        candidates.sort((a, b) -> Double.compare(b.score, a.score));

        return candidates;
    }

    private static class ConflictResolve {

        static Map<Integer, Integer> resolveConflicts(List<CandidateMatch> candidateMatches,
                                                      Map<Integer, Integer> exactMatchMapping){

            Map<Integer, Integer> result = new HashMap<>(exactMatchMapping);

            //Lists to store already matched lines
            List<Integer> oldMatchedLines = new ArrayList<>(exactMatchMapping.keySet());
            List<Integer> newMatchedLines = new ArrayList<>(exactMatchMapping.values());

            for(CandidateMatch c : candidateMatches){

                if (c.score < validMatchScore) {
                    continue;
                }

                if(oldMatchedLines.contains(c.oldIndex)){
                    continue;
                }

                if(newMatchedLines.contains(c.newIndex)){
                    continue;
                }

                result.put(c.oldIndex, c.newIndex);

                oldMatchedLines.add(c.oldIndex);
                newMatchedLines.add(c.newIndex);
            }

            return result;
        }
    }
}