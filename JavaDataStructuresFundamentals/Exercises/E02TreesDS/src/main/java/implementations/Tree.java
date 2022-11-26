package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();

//        this.children.addAll(Arrays.asList(children));
//        for (int i = 0; i < children.length; i++) {
//            children[i].setParent(this);
//        }

    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {

        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        traverseTreeWithRecurrenceDFS(builder, 0, this);


        return builder.toString().trim();
    }

    public List<Tree<E>> traverseWithBFS() {
        StringBuilder builder = new StringBuilder();

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);
        int indent = 0;

        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            allNodes.add(tree);

//            if (tree.getParent() != null && tree.getParent().getKey().equals(this.getKey())) {
//                indent = 2;
//            } else if (tree.children.size() == 0){
//                indent = 4;
//            }
//
////            builder.append(getPadding(indent))
////                    .append(tree.getKey())
////                    .append(System.lineSeparator());

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
//        return builder.toString().trim();
        return allNodes;
    }

    private void traverseTreeWithRecurrenceDFS(StringBuilder builder, int indent, Tree<E> tree) {
        //TODO: check for bottom case

        builder
                .append(this.getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrenceDFS(builder, indent + 2, child);
        }

    }

    private void traverseTreeWithRecurrence(List<Tree<E>> collection, Tree<E> tree) {

        collection.add(tree);

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(collection, child);
        }

    }


    private String getPadding(int size) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }


    @Override
    public List<E> getLeafKeys() {
        return traverseWithBFS()
                .stream()
                .filter(tree -> tree.children.size() == 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {
        List<Tree<E>> allNodes = new ArrayList<>();
        this.traverseTreeWithRecurrence(allNodes, this);
        return allNodes.stream()
                .filter(tree -> tree.parent != null && tree.children.size() > 0)
                .map(Tree::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> trees = traverseWithBFS();

        int maxPath = 0;

        Tree<E> deepestLeafNode = null;

        for (Tree<E> tree : trees) {
            if (tree.isLeaf()) {
                int currentPath = getStepsFromLeafToRoot(tree);
                if (currentPath > maxPath) {
                    maxPath = currentPath;
                    deepestLeafNode = tree;
                }

            }
        }
        return deepestLeafNode;
    }

    private int getStepsFromLeafToRoot(Tree<E> tree) {

        int counter = 0;
        Tree<E> current = tree;

        while (current.parent != null) {
            counter++;
            current = current.parent;
        }
        return counter;
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    @Override
    public List<E> getLongestPath() {
        Stack<Tree<E>> longestPath = new Stack<>();
        Stack<Tree<E>> currentPath = new Stack<>();

        currentPath.push(this);

        getLongestPathDFS(this, longestPath, currentPath);
        List<E> resultPath = new ArrayList<>();

        while (!longestPath.isEmpty()) {
            resultPath.add(longestPath.pop().getKey());
        }

        Collections.reverse(resultPath);

        return resultPath;
    }

    public void getLongestPathDFS(Tree<E> node, Stack<Tree<E>> longestPath, Stack<Tree<E>> currentPath) {
        if (node.getChildren().isEmpty()) {
            if (longestPath.size() < currentPath.size()) {
                longestPath.clear();

                for (Tree<E> currentPathNode : currentPath) {
                    longestPath.push(currentPathNode);
                }
            }
        } else {
            for (Tree<E> childNode : node.children) {
                currentPath.push(childNode);
                getLongestPathDFS(childNode, longestPath, currentPath);
                currentPath.pop();
            }
        }
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> paths = new ArrayList<>();
        Stack<Tree<E>> currentPath = new Stack<>();

        currentPath.push(this);

        getAllPathsWithGivenSum(this, paths, currentPath, sum);

        return paths;
    }

    public void getAllPathsWithGivenSum(Tree<E> node, List<List<E>> paths, Stack<Tree<E>> currentPath, int targetSum) {
        //if not leaf
        if (node.getChildren().isEmpty()) {
            //calculate currentSum
            if (currentPath.stream().mapToInt(x -> (int) x.getKey()).sum() == targetSum) {
                paths.add(new ArrayList<E>(currentPath.stream().map(x -> x.getKey()).collect(Collectors.toList())));
            }
        } else {
            //if leaf / have child
            for (Tree<E> childNode : node.getChildren()) {
                currentPath.push(childNode);

                getAllPathsWithGivenSum(childNode, paths, currentPath, targetSum);

                currentPath.pop();
            }
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> resultTree = new ArrayList<>();

        getSubTreeWithGivenSum(this, sum, 0, resultTree);

        return resultTree;
    }

    public int getSubTreeWithGivenSum(Tree<E> node, int targetSum, int currentSum, List<Tree<E>> resultTree) {
        //if no leaf
//        if (node.getChildren().isEmpty()) {
//            currentSum = (Integer) node.getKey();
//        } else {
            //have child
            currentSum = (Integer) node.getKey();
            for (Tree<E> child : node.children) {
                currentSum += getSubTreeWithGivenSum(child, targetSum, currentSum, resultTree);
            }
            if (currentSum == targetSum) {
                resultTree.add(node);
            }
//        }
        return currentSum;
    }
}



