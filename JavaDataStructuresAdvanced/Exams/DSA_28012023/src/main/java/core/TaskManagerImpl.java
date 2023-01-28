package core;

import models.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManagerImpl implements TaskManager {

    //Map for TasksById
    //Set for all Tasks executed or executable
    Map<String, Task> unexecutedTasksById;
    Map<String,Task> allTasksById;
    Map<String, Task> executedTasks;
    Deque<Task> pendingExecutionTasks;

    public TaskManagerImpl() {
        this.unexecutedTasksById = new LinkedHashMap<>();
        this.allTasksById = new LinkedHashMap<>();
        this.pendingExecutionTasks = new ArrayDeque<>();
        this.executedTasks = new LinkedHashMap<>();
    }

    @Override
    public void addTask(Task task) {
        unexecutedTasksById.put(task.getId(), task);
        pendingExecutionTasks.offer(task);
        allTasksById.put(task.getId(),task);
    }

    @Override
    public boolean contains(Task task) {
        return allTasksById.containsKey(task.getId());
    }

    @Override
    public int size() {
        return unexecutedTasksById.size();
    }

    @Override
    public Task getTask(String taskId) {
        Task result = allTasksById.get(taskId);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public void deleteTask(String taskId) {
        Task toRemove = allTasksById.remove(taskId);
        if (toRemove == null) {
            throw new IllegalArgumentException();
        }
        unexecutedTasksById.remove(toRemove.getId());
        executedTasks.remove(toRemove.getId());
    }

    @Override
    public Task executeTask() {
        Task current = pendingExecutionTasks.poll();
        if (current == null) {
            throw new IllegalArgumentException();
        }
        //save in Executed Tasks
        executedTasks.put(current.getId(), current);
        allTasksById.put(current.getId(), current);

        return current;
    }

    @Override
    public void rescheduleTask(String taskId) {
        Task current = executedTasks.get(taskId);
        if (current == null) {
            throw new IllegalArgumentException();
        }
        pendingExecutionTasks.offer(current);
        this.addTask(current);
    }

    @Override
    public Iterable<Task> getDomainTasks(String domain) {
        List<Task> result = unexecutedTasksById.values()
                .stream()
                .filter(t -> t.getDomain().equals(domain))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Task> getTasksInEETRange(int lowerBound, int upperBound) {
        return unexecutedTasksById.values()
                .stream()
                .filter(t -> t.getEstimatedExecutionTime() >= lowerBound && t.getEstimatedExecutionTime() <= upperBound)
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Task> getAllTasksOrderedByEETThenByName() {
        return allTasksById.values()
                .stream()
                .sorted((t1, t2) -> {
                    int t1EstTimeCount = t1.getEstimatedExecutionTime();
                    int t2EstTimeCount = t2.getEstimatedExecutionTime();

                    if (t1EstTimeCount != t2EstTimeCount) {
                        return t2EstTimeCount - t1EstTimeCount;
                    }
                    int t1NameLength = t1.getName().length();
                    int t2NameLength = t2.getName().length();

                    return t1NameLength - t2NameLength;

                })
                .collect(Collectors.toList());
    }
}
