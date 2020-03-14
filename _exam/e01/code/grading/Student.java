public class Student {
    int perm;

    public Student(int perm) {
	this.perm = perm;
    }

    public int compareTo(Student s) {

	return new Integer(perm).compareTo(new Integer(s.perm));
    }
    
}
    
