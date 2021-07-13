package puzzle;


import java.util.ArrayList;

public class SolutionData {

    private ArrayList<Direction> path;
    private Integer nodesVisited;
    private Integer nodesCreated;
    private Integer largestBorderSize;
    private Integer pathSize;

    public ArrayList<Direction> getPath() {
        return path;
    }

    public SolutionData setPath(ArrayList<Direction> path) {
        this.path = path;
        return this;
    }

    public Integer getNodesVisited() {
        return nodesVisited;
    }

    public SolutionData setNodesVisited(Integer nodesVisited) {
        this.nodesVisited = nodesVisited;
        return this;
    }

    public Integer getNodesCreated() {
        return nodesCreated;
    }

    public SolutionData setNodesCreated(Integer nodesCreated) {
        this.nodesCreated = nodesCreated;
        return this;
    }

    public Integer getLargestBorderSize() {
        return largestBorderSize;
    }

    public SolutionData setLargestBorderSize(Integer largestBorderSize) {
        this.largestBorderSize = largestBorderSize;
        return this;
    }

    public Integer getPathSize() {
        return pathSize;
    }

    public SolutionData setPathSize(Integer pathSize) {
        this.pathSize = pathSize;
        return this;
    }
}
