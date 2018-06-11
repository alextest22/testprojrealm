package com.test.testprojrealm.data.network.pojo;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {


        @SerializedName("result_count")
        @Expose
        private int resultCount;
        @SerializedName("images")
        @Expose
        private List<ImageResponse> ImageResponses = new ArrayList<ImageResponse>();

        public int getResultCount() {
            return resultCount;
        }

        public void setResultCount(int resultCount) {
            this.resultCount = resultCount;
        }

        public List<ImageResponse> getImageResponses() {
            return ImageResponses;
        }

        public void setImageResponses(List<ImageResponse> ImageResponses) {
            this.ImageResponses = ImageResponses;
        }

    
}
