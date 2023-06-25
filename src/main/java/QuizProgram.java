import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class QuizProgram {
    public static void main(String[] args) throws IOException, ParseException {
        char ch = 's';
        String QfileName = "./src/main/resources/QuestionList.json";
        String LfileName = "./src/main/resources/UserLogin.json";

        JSONParser parser=new JSONParser();
        JSONArray loginArray = (JSONArray) parser.parse(new FileReader(LfileName));
        JSONObject adminObj = (JSONObject) loginArray.get(0);
        JSONObject studentObj = (JSONObject) loginArray.get(1);
       // System.out.println(adminObj);

        String adminObjString =(String) adminObj.get("username");
        //System.out.println(adminObjString);
        String studentObjString = (String) studentObj.get("username");
        //System.out.println(studentObjString);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String name = sc.nextLine();
        System.out.println("Enter password: ");
        String pass = sc.nextLine();

            if (name.equals(adminObjString)) {

                System.out.println("Welcome admin! Please create new questions in the question bank... ");

                do {
                    JSONParser jsonParser = new JSONParser();
                    JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(QfileName));
                    // LinkedHashMap<String, Object> quesObj = new LinkedHashMap<String, Object>();
                    JSONObject quesObj = new JSONObject();

                    quesObj.put("Question", sc.nextLine());

                    System.out.println("Input options: ");

                    System.out.println("Option a: ");
                    quesObj.put("a:", sc.nextLine());

                    System.out.println("Option b: ");
                    quesObj.put("b:", sc.nextLine());

                    System.out.println("Option c: ");
                    quesObj.put("c:", sc.nextLine());

                    System.out.println("Option d: ");
                    quesObj.put("d:", sc.nextLine());

                    System.out.println("Please input the correct ans: ");
                    quesObj.put("Correct:", sc.nextLine());

                    jsonArray.add(quesObj);


                    FileWriter fileWriter = new FileWriter(QfileName);
                    fileWriter.write(jsonArray.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();

                    System.out.println("Saved successfully! Do you want to add more questions? " +
                            "(press 's' for start or 'q' for quit)");
                    ch = sc.next().charAt(0);
                    sc.nextLine();
                }
                while (ch != 'q');


            } else if (name.equals(studentObjString)) {
                System.out.println("Welcome " + name + " to the quiz! We will throw you 10 questions. " +
                        "Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
                Scanner in = new Scanner(System.in);
                String str = in.nextLine();


                if (str.contains("s")) {


                    do {

                        JSONParser jsonParser = new JSONParser();
                        Object obj = jsonParser.parse(new FileReader(QfileName));
                        JSONArray jsonArray = (JSONArray) obj;

                        int marks = 0;
                        for (int i = 0; i < 2; i++) {

                            int input = new Random().nextInt(jsonArray.size());
                            JSONObject quesObj = (JSONObject) jsonArray.get(input);

                            String question = (String) quesObj.get("Question");
                            String optionA = (String) quesObj.get("a:");
                            String optionB = (String) quesObj.get("b:");
                            String optionC = (String) quesObj.get("c:");
                            String optionD = (String) quesObj.get("d:");
                            String correct = (String) quesObj.get("Correct:");

                            System.out.println("\n" + (i + 1) + ": " + question);
                            System.out.println("a. " + optionA);
                            System.out.println("b. " + optionB);
                            System.out.println("c. " + optionC);
                            System.out.println("d. " + optionD);

                            String answer = sc.nextLine();
//                     String correctAnswer = (String) quesObj.get("Correct:");
                            if (answer.contains(correct)) {
                                System.out.println("Correct!");
                                marks++;
                            } else {
                                System.out.println("Not correct!");
                            }
                        }

                        if (marks >= 8) {
                            System.out.println("\n" + " Excellent! You have got " + marks + " out of 10");
                        } else if (marks >= 5 && marks < 8) {
                            System.out.println("\n" + " Good. You have got " + marks + " out of 10");
                        } else if (marks >= 2 && marks < 5) {
                            System.out.println("\n" + " Very poor! You have got " + marks + " out of 10");
                        } else {
                            System.out.println("\n" + " Very sorry you are failed. You have got " + marks + " out of 10");
                        }

                        System.out.println("\n" +" Would you like to start again? press s for start or q for quit");

                        ch = sc.next().charAt(0);
                        sc.nextLine();
                    } while (ch != 'q');

                }else {
                    System.out.println(" Please input correct information!!!!");
                }
            }

   }
}