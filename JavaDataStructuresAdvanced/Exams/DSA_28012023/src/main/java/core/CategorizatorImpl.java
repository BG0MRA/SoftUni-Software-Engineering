package core;

import models.Category;

import java.util.*;
import java.util.stream.Collectors;

public class CategorizatorImpl implements Categorizator {
    private final Map<String, Category> categoriesById;
    private final Map<String, LinkedList<Category>> childrenCategories;
    private final Map<String, LinkedList<Category>> parentCategories;


    public CategorizatorImpl() {
        this.categoriesById = new LinkedHashMap<>();
        this.childrenCategories = new LinkedHashMap<>();
        this.parentCategories = new LinkedHashMap<>();
    }

    @Override
    public void addCategory(Category category) {
        if (categoriesById.containsKey(category.getId())) {
            throw new IllegalArgumentException();
        }
        categoriesById.put(category.getId(), category);
        parentCategories.put(category.getId(), new LinkedList<>());

    }

    @Override
    public void assignParent(String childCategoryId, String parentCategoryId) {
        if (!categoriesById.containsKey(childCategoryId) || !categoriesById.containsKey(parentCategoryId)) {
            throw new IllegalArgumentException();
        }

        if (parentCategories.get(parentCategoryId).contains(categoriesById.get(childCategoryId))) {
            throw new IllegalArgumentException();
        }
        parentCategories.get(parentCategoryId).add(categoriesById.get(childCategoryId));
    //    childrenCategories.computeIfAbsent(parentCategoryId, k -> new LinkedList<>()).add(categoriesById.get(childCategoryId));

    }

    @Override
    public void removeCategory(String categoryId) {
        if (!categoriesById.containsKey(categoryId)) {
            throw new IllegalArgumentException();
        }
        categoriesById.remove(categoryId);
        LinkedList<Category> children = parentCategories.get(categoryId);
        for (Category child : children) {
            removeCategory(child.getId());
        }
        parentCategories.remove(categoryId);
        removeChildrenRecursively(categoryId);
//        removeParentRecursively(categoryId);

    }
    private void removeChildrenRecursively(String categoryId) {
        if (!childrenCategories.containsKey(categoryId)) {
            return;
        }
        for (Category child : childrenCategories.get(categoryId)) {
            removeChildrenRecursively(child.getId());
        }
        childrenCategories.remove(categoryId);
    }
//    private void removeParentRecursively(String categoryId) {
//        if (!parentCategories.containsKey(categoryId)) {
//            return;
//        }
//        for (Category child : parentCategories.get(categoryId)) {
//            removeChildrenRecursively(child.getId());
//        }
//        parentCategories.remove(categoryId);
//    }

    @Override
    public boolean contains(Category category) {
        return categoriesById.containsKey(category.getId());
    }

    @Override
    public int size() {
        return categoriesById.size();
    }

    @Override
    public Iterable<Category> getChildren(String categoryId) {
        if (!categoriesById.containsKey(categoryId)) {
            throw new IllegalArgumentException();
        }
        List<Category> children = new LinkedList<>();
   //     getChildrenRecursively(categoryId, children);
        getChildrenHelper(categoryId, children);
        return children;
    }
    private void getChildrenHelper(String categoryId, List<Category> children) {
        for (Category child : parentCategories.get(categoryId)) {
            children.add(child);
            getChildrenHelper(child.getId(), children);
        }
    }
//    private void getChildrenRecursively(String categoryId, List<Category> children) {
//        if (!childrenCategories.containsKey(categoryId)) {
//            return;
//        }
//        for (Category child : childrenCategories.get(categoryId)) {
//            children.add(child);
//            getChildrenRecursively(child.getId(), children);
//        }
//    }

    @Override
    public Iterable<Category> getHierarchy(String categoryId) {
        if (!categoriesById.containsKey(categoryId)) {
            throw new IllegalArgumentException();
        }
        Category currentCategory = categoriesById.get(categoryId);
        LinkedList<Category> hierarchy = new LinkedList<>();
        while (currentCategory != null) {
            hierarchy.addFirst(currentCategory);
            currentCategory = currentCategory.getParent();
        }
        return hierarchy;
    }

    @Override
    public Iterable<Category> getTop3CategoriesOrderedByDepthOfChildrenThenByName() {
        return categoriesById.values().stream()
                .sorted((c1, c2) -> {
                    int depthCmp = Integer.compare(c2.getDepthOfChildCategories(), c1.getDepthOfChildCategories());
                    if (depthCmp != 0) {
                        return depthCmp;
                    }
                    return c1.getName().compareTo(c2.getName());
                })
                .limit(3)
                .collect(Collectors.toList());
    }


}
