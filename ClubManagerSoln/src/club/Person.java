package club;

public class Person {

    private String surname;
    private String firstName;
    private String secondName;

    public Person (String surname, String firstName, String secondName) {
        this.surname = surname;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void show() {
        String fullName = firstName;
        if (secondName != null) {
            fullName += " " + secondName;
        }
        fullName += " " + surname;
        System.out.println (fullName);
    }

	@Override
	public String toString() {
		return "Person [surname=" + surname + ", firstName=" + firstName
				+ ", secondName=" + secondName + "]";
	}
    
    
}
