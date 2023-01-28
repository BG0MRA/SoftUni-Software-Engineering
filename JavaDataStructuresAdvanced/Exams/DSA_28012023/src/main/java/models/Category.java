package models;

import java.util.*;

public class Category {
    private String id;

    private String name;

    private String description;

    private Set<Category> children;

    private Category parent;
    private int depthOfChildCategories;

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.children = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getChildren(){
        return children;
    }
    public Category getParent() {
        return parent;
    }
    public void setParent(Category parent) {
        this.parent = parent;
    }
    public void addChild(Category child) {
        children.add(child);
    }

    public void removeChild(Category child) {
        children.remove(child);
    }

    public int getDepthOfChildCategories() {
        if (children.isEmpty()) {
            return 0;
        }
        int maxDepth = 0;
        for (Category child : children) {
            maxDepth = Math.max(maxDepth, child.getDepthOfChildCategories());
        }
        return maxDepth + 1;
    }

}
