import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String starter =   "   (=^ ･ｪ･^=) < ";
        String buffer =    "                ";
        String sleepy =    "   (=^ ‐ｪ‐^=) < ";
        String confused =  "   (=^ ･x･^=)? < ";
        String happy =     "   (=^ ･ω･^=)❀ < ";

        // Welcome text
        System.out.println(starter + "Hello! I'm TiTi~ ");
        System.out.println(buffer + "What would you like to do nya? ");

        String input = sc.nextLine();
        while (true) {
            String cue = input.split(" ", 2)[0];

            // exit cue
            if (cue.equals("bye")) {
                System.out.println(sleepy + "Already done? Come back again soon nya~");
                break;
            }

            // display list
            if (cue.equals("list")) {
                int numberOfTasks = list.size();
                // no tasks
                if (numberOfTasks == 0) {
                    System.out.println(starter + "Looks like you have no tasks nya~");
                    input = sc.nextLine();
                    continue;
                }
                // print task list
                System.out.println(starter + "Have you competed these tasks nya?");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(buffer + (i + 1) + ". " + list.get(i));
                }
                input = sc.nextLine();
                continue;
            }

            // mark complete task
            if (cue.equals("done")) {
                int taskNumber = Integer.parseInt("" + input.charAt(5));
                int numberOfTasks = list.size();
                if (taskNumber > numberOfTasks) {
                    System.out.println(confused + "Nya?... I can't find the task... ");
                } else {
                    Task task = list.get(taskNumber - 1);
                    task.complete();
                    System.out.println(happy + "Nya! You've worked hard haven't you!");
                    System.out.println(buffer + "I'll mark this task as done: ");
                    System.out.println(buffer + task.toString());
                }
                input = sc.nextLine();
                continue;
            }

            // add todo to list
            if (cue.equals("todo")) {
                String task = input.split(" ", 2)[1];
                ToDo todo = new ToDo(task);
                list.add(todo);
                System.out.println(starter + "A new task? I'll add this to the list: ");
                System.out.println(buffer + "  " + todo.toString());
                System.out.println(buffer + countTasks(list));
                System.out.println(buffer + "Don't forget to complete it nya~ ");
                input = sc.nextLine();
                continue;
            }

            // add deadline to list
            if (cue.equals("deadline")) {
                String[] arr = input.split(" ", 2)[1].split(" /by ", 2);
                String task = arr[0];
                String time = arr[1];

                Deadline deadline = new Deadline(task, time);
                list.add(deadline);
                System.out.println(starter + "A new deadline? Sounds tough nya...");
                System.out.println(buffer + "  " + deadline.toString());
                System.out.println(buffer + countTasks(list));
                System.out.println(buffer + "Gambatte nya~ ");
                input = sc.nextLine();
                continue;
            }

            // add event to list
            if (cue.equals("event")) {
                String[] arr = input.split(" ", 2)[1].split(" /at ", 2);
                String task = arr[0];
                String time = arr[1];

                Event event = new Event(task, time);
                list.add(event);
                System.out.println(starter + "A new event? Let me record it down: ");
                System.out.println(buffer + "  " + event.toString());
                System.out.println(buffer + countTasks(list));
                System.out.println(buffer + "I'll be waiting nya~ ");
                input = sc.nextLine();
                continue;
            }

            System.out.println(confused + "Nya?... I can't find what you are looking for... ");
            input = sc.nextLine();
        }
    }

    static String countTasks(ArrayList<Task> list) {
        if (list.size() == 1) {
            return "We now have " + list.size() + " task on our list. ";
        } else {
            return "We now have " + list.size() + " tasks on our list. ";
        }
    }

}
