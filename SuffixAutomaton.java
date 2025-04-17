import java.util.*;

public class SuffixAutomaton {
    static class State {
        int len, link;
        Map<Character, Integer> next = new HashMap<>();

        State() {
            len = 0;
            link = -1;
        }
    }

    List<State> st = new ArrayList<>();
    int size, last;

    public SuffixAutomaton(String s) {
        st.add(new State()); // initial state
        size = 1;
        last = 0;

        for (char c : s.toCharArray()) {
            saExtend(c);
        }
    }

    private void saExtend(char c) {
        int cur = size++;
        st.add(new State());
        st.get(cur).len = st.get(last).len + 1;

        int p = last;
        while (p != -1 && !st.get(p).next.containsKey(c)) {
            st.get(p).next.put(c, cur);
            p = st.get(p).link;
        }

        if (p == -1) {
            st.get(cur).link = 0;
        } else {
            int q = st.get(p).next.get(c);
            if (st.get(p).len + 1 == st.get(q).len) {
                st.get(cur).link = q;
            } else {
                int clone = size++;
                st.add(new State());
                st.get(clone).len = st.get(p).len + 1;
                st.get(clone).next.putAll(st.get(q).next);
                st.get(clone).link = st.get(q).link;

                while (p != -1 && st.get(p).next.get(c) == q) {
                    st.get(p).next.put(c, clone);
                    p = st.get(p).link;
                }

                st.get(q).link = st.get(cur).link = clone;
            }
        }

        last = cur;
    }

    // Check if string is a substring of original
    public boolean isSubstring(String query) {
        int current = 0;
        for (char c : query.toCharArray()) {
            if (!st.get(current).next.containsKey(c)) return false;
            current = st.get(current).next.get(c);
        }
        return true;
    }

    // Count total number of different substrings
    public long countDistinctSubstrings() {
        long[] dp = new long[st.size()];
        Arrays.fill(dp, -1);
        return dfs(0, dp);
    }

    private long dfs(int u, long[] dp) {
        if (dp[u] != -1) return dp[u];
        long res = 1; // include empty string
        for (int v : st.get(u).next.values()) {
            res += dfs(v, dp);
        }
        return dp[u] = res;
    }

    // Driver
    public static void main(String[] args) {
        String input = "ababa";
        SuffixAutomaton sa = new SuffixAutomaton(input);

        System.out.println("Is 'aba' a substring? " + sa.isSubstring("aba"));
        System.out.println("Is 'abc' a substring? " + sa.isSubstring("abc"));
        System.out.println("Total different substrings: " + (sa.countDistinctSubstrings() - 1)); // excluding empty string
    }
}