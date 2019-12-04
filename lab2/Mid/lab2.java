import java.util.Scanner;
import java.util.Arrays;

class StudentList
{
    static Student[] studentList = new Student[1];

    public static void printList()
    {
        for (Student student : studentList)
        {
            try {
                System.out.println("Last name: " + student.getLastName() + " First name: " + student.getFirstName() + " Birthday: " + student.getBirthday()[0] + "." + student.getBirthday()[1] + "." + student.getBirthday()[2] + " Address: " + student.getAddress());
            }catch(NullPointerException e){}

        }
    }

    public static void addStudent() throws RuntimeException
    {
        String lastName, firstName, line;
        String address;
        int[] birthday = new int[3];
        Scanner in = new Scanner(System.in);
        System.out.print("Enter last name of student ->");
        lastName = in.nextLine();
        System.out.println();
        System.out.print("Enter first name of student ->");
        firstName = in.nextLine();
        System.out.println();
        System.out.print("Enter birthdate of student(DD.MM.YYYY) ->");
        line = in.nextLine();
        System.out.println();
        try {
            birthday[0] = (line.charAt(0) - 48) * 10 + line.charAt(1) - 48;
            birthday[1] = (line.charAt(3) - 48) * 10 + line.charAt(4) - 48;
            birthday[2] = (line.charAt(6) - 48) * 1000 + (line.charAt(7) - 48) * 100 + (line.charAt(8) - 48) * 10 + line.charAt(9) - 48;

            if (birthday[1] <= 1 && birthday[1] >= 12 && birthday[0] <= 1 && birthday[0] >= 31)
               throw new RuntimeException();
                //System.out.println("Error");
        }
        catch(RuntimeException e)
        {
            System.out.println("Wrong date format!!! Try again");
            addStudent();
            return;
        }
        System.out.print("Enter address of student ->");
        address = in.nextLine();
        System.out.println();
        studentList[studentList.length - 1] = new Student(lastName, firstName, birthday, address);
        studentList = Arrays.copyOf(studentList, studentList.length + 1);
    }

    public static void main(String[] args)
    {
        System.out.println("1.Add new student");
        System.out.println("2.Print all students");
        Scanner in = new Scanner(System.in);
        int mode = in.nextInt();
        if(mode == 1) {
            addStudent();
            main(args);
        }
        if(mode == 2) {
            printList();
            main(args);
        }
        else
            return;
    }
}

class Student
{
    private String lastName;
    private String firstName;
    private int[] birthday = new int[3];
    private String address;

    Student(String _lastName, String _firstName, int[] _birthday, String _address)
    {
        this.lastName = _lastName;
        this.firstName = _firstName;
        this.birthday = _birthday;
        this.address = _address;
    }

    public void setLastName(String _lastName)
    {
        this.lastName = _lastName;
    }

    public void setFirstName(String _firstName)
    {
        this.firstName = _firstName;
    }

    public void setBirthday(int[] _birthday)
    {
        this.birthday = _birthday;
    }

    public void setAddress(String _address)
    {
        this.address = _address;
    }


    public String getLastName()
    {
        return this.lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public int[] getBirthday()
    {
        return this.birthday;
    }

    public String getAddress()
    {
        return this.address;
    }

}

