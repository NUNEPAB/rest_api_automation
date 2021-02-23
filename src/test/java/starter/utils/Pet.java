package starter.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Pet {

    private long id;

    private HashMap<String,String> category;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String[] photoUrl;

    private List<HashMap<String,String>> tags = new ArrayList<HashMap<String, String>>();

    private HashMap<String,String> tag;

    public HashMap<String, String> getTag() {
        return tag;
    }

    public void setTag(HashMap<String, String> tag) {
        this.tag = tag;
    }

    public HashMap<String,String> getCategory() {
        return category;
    }

    public void setCategory(HashMap<String,String> category) {
        this.category = category;
    }

    public String[] getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String[] photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<HashMap<String, String>> getTags() {
        return tags;
    }

    public void setTags(List<HashMap<String,String>> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String petToString =  "{" +
                "category : " + category +
                ", id : " + id +
                ", name : '" + name + '\'' +
                ", photoUrls : " + Arrays.toString(photoUrl) +
                ", tags : " + getTagsAsArray() +
                ", status : '" + status + '\'' +
                '}';
        return petToString.replace("=",":");
    }

    public void addTag(HashMap<String,String> tag){
        this.tags.add(this.tags.size(),tag);
    }

    public HashMap<String,String>[] getTagsAsArray(){
        return this.tags.toArray(new HashMap[0]);
    }
}
