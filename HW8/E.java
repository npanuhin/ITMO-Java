import java.util.Arrays;

import java.io.InputStreamReader;
// import java.io.StringReader;
// import java.io.InputStream;
import java.io.BufferedReader;
// import java.io.Reader;

// import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStreamWriter;
// import java.io.FileOutputStream;
import java.io.BufferedWriter;



class IntList {
    public int[] numbers = new int[0];
    public int size = 0;

    public void set(int pos, int value) {
        while (pos >= this.numbers.length) {
            this.numbers = Arrays.copyOf(this.numbers, Math.max(this.numbers.length * 2, 1));
        }
        this.numbers[pos] = value;
        this.size = Math.max(this.size, pos + 1);
    }

    public void add(int value) {
        set(size, value);
    }

    public int get(int pos) {
        return this.numbers[pos];
    }
}

class PairDepthVertex {
    public int depth = -1, vertex;
}


public class E {

    private static int nextInt(BufferedReader reader) throws IOException {
        char c;
        while (Character.isWhitespace(c = (char)reader.read()));

        int res = 0;
        while (!Character.isWhitespace(c)) {
            res = res * 10 + c - '0';
            c = (char) reader.read();
        }
        return res;
    }

    private static PairDepthVertex findFurthest(IntList[] graph, boolean[] teamCities, int[] pred, int curVertex) {
        PairDepthVertex result = new PairDepthVertex();
        if (teamCities[curVertex]) {
            result.depth = 0;
            result.vertex = curVertex;
        }

        for (int i = 0; i < graph[curVertex].size; i++) {
            int nextVertex = graph[curVertex].numbers[i];
            if (nextVertex == pred[curVertex]) {
                continue;
            }

            pred[nextVertex] = curVertex;
            PairDepthVertex nextData = findFurthest(graph, teamCities, pred, nextVertex);
            if (nextData.depth != -1 && nextData.depth > result.depth) {
                result.depth = nextData.depth;
                result.vertex = nextData.vertex;
            }
        }

        result.depth++;
        return result;
    }

    private static boolean checkDepth(IntList[] graph, boolean[] teamCities, int curVertex, int pred, int curDepth) {
        if (teamCities[curVertex] && curDepth != 0) {
            return false;
        }
        curDepth--;

        for (int i = 0; i < graph[curVertex].size; i++) {
            if (graph[curVertex].numbers[i] == pred) {
                continue;
            }
            if (!checkDepth(graph, teamCities, graph[curVertex].numbers[i], curVertex, curDepth)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // Input:
        int n = nextInt(input), m = nextInt(input), v, u;

        IntList[] graph = new IntList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new IntList();
        }
        for (int i = 0; i < n - 1; ++i) {
            v = nextInt(input) - 1;
            u = nextInt(input) - 1;
            graph[v].add(u);
            graph[u].add(v);
        }

        boolean[] teamCities = new boolean[n];
        int cityWithTeam = 0;
        for (int i = 0; i < m; i++) {
            teamCities[cityWithTeam = nextInt(input) - 1] = true;
        }

        input.close();



        // Solution & output:
        BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        int[] pred = new int[n];
        PairDepthVertex p = findFurthest(graph, teamCities, pred, cityWithTeam);

        int middleVertex = p.vertex;
        for (int i = 0; i < p.depth / 2; i++) {
            middleVertex = pred[middleVertex];
        }

        if (checkDepth(graph, teamCities, middleVertex, middleVertex, p.depth / 2)) {
            output.write("YES");
            output.newLine();
            output.write(String.valueOf(middleVertex + 1));
        } else {
            output.write("NO");
        }
        output.newLine();

        output.close();
    }
}
