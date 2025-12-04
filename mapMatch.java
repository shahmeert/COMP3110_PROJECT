import java.util.*;

public class mapMatch {

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
        
        
        return new lineMapResult(mapping, oldLines, newLines);
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

    private List<CandidateMatch> findCandidates(List<String> oldLines, List<String> newLines, Map<Integer, Integer> exactMatchesMap){


        
    }

}
