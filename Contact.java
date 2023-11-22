
public class Contact{
    String name;
    String phoneNumber;
    String address;
    String email;
    String website;

    public Contact(String name, String phoneNumber, String address, String email, String website) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.website = website;
    }

    public String toString(){
        

        return " Name : " +this.name+  " Phone : "+ this.phoneNumber;
    }

    public static void main(String[] args) {

        Contact c1 = new Contact("Edd", "Edd", "Edd", "Edd", "Edd");
        System.out.println(c1);
        
    }
}