package comm.mrspdd.lockdownsevaapp.Models;

public class ShopsModelClass {

    private String mName;
    private String mImageUrl;
    private String mAddress;
    private String mPhone;

    public ShopsModelClass() {
        //empty constructor needed
    }

    public ShopsModelClass(String name, String image, String address, String phone) {
        if (name.trim().equals("")) {
            name = "No Name";
        }


        mName = name;
        mImageUrl = image;
        mAddress = address;
        mPhone = phone;

    }

    public String getImage() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setimage(String image) {
        mImageUrl = image;
    }

    public void setaddress(String address) {
        mAddress = address;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getPhone() {
        return mPhone;
    }

    public void setphone(String phone) {
        mPhone = phone;
    }


}

