package finalExam;

import java.util.*;

public class P03Followers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        //user data: follower , likes comments

        Map<String, Integer> followerLikes = new LinkedHashMap<>();
        Map<String, Integer> followerComments = new LinkedHashMap<>();
        List<String> followers = new ArrayList<>();

        while (!command.equals("Log out")) {

            if (command.contains("New follower")) {
                //command "New follower: {userName}"
                String userName = command.split(":\\s+")[1];
                followerLikes.putIfAbsent(userName, 0);
                followerComments.putIfAbsent(userName, 0);

                //save the names for iteration later
                if (!followers.contains(userName)) {
                    followers.add(userName);
                }

            } else if (command.contains("Like")) {
                //command "Like: {userName}: {count}"
                String userName = command.split(":\\s+")[1];
                int count = Integer.parseInt(command.split(":\\s+")[2]);

                //userName doesn't exist
                if (!followerLikes.containsKey(userName)) {
                    followerLikes.put(userName, count);
                    followerComments.putIfAbsent(userName,0);
                } else {
                    //userName exist
                    int getLikes = followerLikes.get(userName);
                    getLikes += count;
                    followerLikes.put(userName, getLikes);
                }

                //save the names for iteration later
                if (!followers.contains(userName)) {
                    followers.add(userName);
                }


            } else if (command.contains("Comment")) {
                //command "Comment: {username}"
                String userName = command.split(":\\s+")[1];

                //userName doesn't exist
                if (!followerComments.containsKey(userName)) {
                    followerComments.put(userName, 1);
                    followerLikes.putIfAbsent(userName,0);
                } else {
                    //userName exist
                    int getComments = followerComments.get(userName);
                    getComments++;
                    followerComments.put(userName, getComments);
                }

                //save the names for iteration later
                if (!followers.contains(userName)) {
                    followers.add(userName);
                }


            } else if (command.contains("Blocked")) {
                //command "Blocked: {username}"
                String userName = command.split(":\\s+")[1];

                if (followerLikes.containsKey(userName) || followerComments.containsKey(userName)) {
                    //follower exist
                    if (followerLikes.containsKey(userName)) {
                        // in map followerLikes
                        followerLikes.remove(userName);
                    }
                    if (followerComments.containsKey(userName)) {
                        // in map followerComments
                        followerComments.remove(userName);
                    }
                } else {
                    //follower doesn't exist
                    System.out.printf("%s doesn't exist.\n", userName);
                }
            }


            //update command
            command = scanner.nextLine();
        }

        //result map
        Map<String, Integer> resultFollowers = new LinkedHashMap<>();
        for (String username : followers) {
            if (followerLikes.containsKey(username) && followerComments.containsKey(username)) {
                int comments = followerComments.get(username);
                int likes = followerLikes.get(username);
                resultFollowers.put(username, comments + likes);
            } else if ((!followerLikes.containsKey(username)) && followerComments.containsKey(username)) {
                int comments = followerComments.get(username);
                resultFollowers.put(username, comments);

            } else if (followerLikes.containsKey(username) && (!followerComments.containsKey(username))) {
                int likes = followerLikes.get(username);
                resultFollowers.put(username, likes);

            }
        }

        System.out.printf("%d followers\n", resultFollowers.size());
        resultFollowers.entrySet().forEach(entry -> {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        });

    }
}
