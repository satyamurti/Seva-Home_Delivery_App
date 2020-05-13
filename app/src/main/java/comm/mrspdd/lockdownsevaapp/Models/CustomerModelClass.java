package comm.mrspdd.lockdownsevaapp.Models;

public class CustomerModelClass {
    String phone;
    String name;
    String address;


    public CustomerModelClass(String phone, String maddress, String mname) {
        this.phone = phone;
        this.address = maddress;
        this.name = mname;
    }

    public String getPhone() {
        return phone;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


}
