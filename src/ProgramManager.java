import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramManager {

    public static void main(String[] args) {

        boolean ProgramStatus = true;
        String[] command;

        ArrayList<Program> programList = GetAllProgramInfo();
        CommandGUI();

        while(ProgramStatus) {
            command = CommandInput("MainCommand").split(" ", 2);

            switch(command[0]) {
                case "command":
                    CommandGUI();
                    break;
                case "list":
                    ShowList(programList);
                    break;
                case "opis":
                    if(command.length != 1) ShowDescriptionProgram(command[1]);
                    else System.out.println("Brak argumentu");
                    break;
                case "run":
                    if(command.length != 1) RunProgram(command[1], FindType(programList, command[1]));
                    else System.out.println("Brak argumentu");
                    break;
                case "exit":
                    ProgramStatus = false;
                    break;
                default:
                    System.out.println("Polecenie nie rozpoznane");
            }
        }
    }

    public static void CommandGUI() {
        System.out.println("--------------------");
        System.out.println("Lista komend:");
        System.out.println("'command' - ponownie wyswietla komendy");
        System.out.println("'list' - wyswietlenie listy programow w bazie");
        System.out.println("'opis [NazwaPliku]' - wyswietlenie opisu programu");
        System.out.println("'run [NazwaPliku]' - uruchamia program");
        System.out.println("'exit' - wyjscie z programu");
        System.out.println("--------------------");
    }

    public static String CommandInput(String local) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(local + ">: ");
        return scanner.nextLine();
    }

    public static ArrayList<Program> GetAllProgramInfo()
    {
        ArrayList<Program> programList = new ArrayList<>();

        //!!! DO Wymiany
        String[] pathnames = new File("src/Source").list();

        for(String name : pathnames) {
            programList.add(new Program(name, GetTypeProgram(name)));
        }

        return programList;
    }

    public static ProgramType GetTypeProgram(String ProgramName) {
        return (ProgramName.endsWith(".java") || ProgramName.endsWith(".class")) ? ProgramType.FILE : ProgramType.FOLDER;
    }

    public static void ShowList(ArrayList<Program> programList) {
        System.out.println("--------------------");

        for (Program program : programList) {
            System.out.println(program.getName());
        }

        System.out.println("--------------------");
    }

    public static void ShowDescriptionProgram(String programName) {

        System.out.println("--------------------");
        System.out.println(GetDescriptionProgram(programName, false));
        System.out.println("--------------------");
    }

    public static String GetDescriptionProgram(String programName, boolean getArgue) {
        String target;
        if(!getArgue) {
            target = "Brak opisu programu, lub niewłaściwy program";
        } else target = "@None";

        try {
            //!!! Do Wymiany
            File file = new File("src/Config/DescriptionProgram.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.equals(programName + ":") && !getArgue)
                {
                    target = scanner.nextLine();
                    break;
                }
                else if(line.equals(programName + "[argue]:") && getArgue)
                {
                    target = scanner.nextLine();
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            target = "Plik nie został odnaleziony";
        } catch(Exception e) {
            target = "Błąd podczas odczytywania pliku";
        }

        return target;
    }

    public static ProgramType FindType(ArrayList<Program> programs, String programName) {
        for(Program element : programs) {
            if(element.getName().equals(programName)) return element.getType();
        }
        return null;
    }

    public static void RunProgram(String programName, ProgramType type) {

        if(type==null) {
            System.out.println("Nie znaleziono programu");
            return;
        }

        String argue = GetDescriptionProgram(programName, true);

        System.out.println("--------------------");
        System.out.println(argue);
        System.out.println("--------------------");

        String args=null;
        if(!argue.equals("@None")) {
            args = CommandInput(programName);
        }

        try {
            Class c;
            if(type==ProgramType.FOLDER) {
                c = Class.forName("Source." + programName + ".Application");
            } else {
                c = Class.forName("Source." + programName);
            }
            Method method = c.getMethod("run", String.class);
            if(args==null) {
                method.invoke(null);
            } else {
                method.invoke(null, args);
            }
        } catch(ClassNotFoundException e) {
            System.out.println("Nie można odnależć programu");
            return;
        } catch(IllegalArgumentException e) {
            System.out.println("Możliwa nieprawidłowa ilosc argumentow");
            return;
        } catch(Exception e) {
            System.out.println("Wystąpił błąd" + e.toString());
            return;
        }
    }
}

class Program {

    private String name;
    private ProgramType type;

    public Program(String name, ProgramType type) {
        this.type = type;

        if(type==ProgramType.FILE) {
            this.name = name.substring(0, name.length()-5);
        }
    }

    public String getName() {
        return name;
    }

    public ProgramType getType() {
        return type;
    }
}

enum ProgramType {
    FILE, FOLDER
}
