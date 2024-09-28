package Graph;

/**
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 * <p>
 * If the town judge exists, then:
 * <p>
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.
 * <p>
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * All the pairs of trust are unique.
 * ai != bi
 * 1 <= ai, bi <= n
 **/
public class findTheTownJudge997 {

    public static int findJudge(int n, int[][] trust) {
        int[] Trusted = new int[n+1];
        for(int[] person : trust){
            Trusted[person[0]]--;
            Trusted[person[1]]++;
        }
        for(int i = 1;i < Trusted.length;i++){
            if(Trusted[i] == n-1) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] trustMatrix = new int[][]{
                {1, 3},
                {2, 3}
        };
        System.out.println(findJudge(2, trustMatrix));
    }
}
