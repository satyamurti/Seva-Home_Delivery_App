package comm.mrspdd.lockdownsevaapp.Models;

public class OrderHistoryModelClass {

    private String mShopname;
    private String mTime;
    private String mPhone;
    private String mStatus;
    private String mPeth;

    public OrderHistoryModelClass() {
        //empty constructor needed
    }


    public OrderHistoryModelClass(String shopname, String time, String shopphone, String status, String peth) {
        if (shopname.trim().equals("")) {
            shopname = "No Name";
        }


        mPhone = shopphone;
        mShopname = shopname;
        mTime = time;
        mStatus = status;
        mPeth = peth;

    }

    public String getPeth() {
        return mPeth;
    }

    public void setPeth(String Peth) {
        mPeth = Peth;
    }

    public String getTime() {
        return mTime;
    }

    public String getShopname() {
        return mShopname;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setShopname(String shopname) {
        mShopname = shopname;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }


    public String getShopphone() {
        return mPhone;
    }

    public void setShopphone(String shopphone) {
        mPhone = shopphone;
    }


}

