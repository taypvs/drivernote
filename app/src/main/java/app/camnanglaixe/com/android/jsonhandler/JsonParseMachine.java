package app.camnanglaixe.com.android.jsonhandler;

import org.json.JSONObject;

import app.camnanglaixe.com.android.models.Topic;

/**
 * Created by taypham on 29/11/2016.
 */
public class JsonParseMachine {


//    public static JSONObject parseFile(Context context, String fileAssetName){
//        try {
//            JSONParser jsonParser = new JSONParser();
//            AssetFileDescriptor descriptor = context.getAssets().openFd(fileAssetName);
//            FileReader reader = new FileReader(descriptor.getFileDescriptor());
//            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
//            return jsonObject;
//        }catch (IOException e){
//            e.printStackTrace();
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Topic parseTopic(JSONObject jsonObject){
        Topic newTopic;

        String id = jsonObject.optString("id","");
        String version = jsonObject.optString("version", "");
        String title = jsonObject.optString("title","");
        String icon = jsonObject.optString("icon","");
        String type = jsonObject.optString("type","");

        newTopic = new Topic(id, title, type, icon, version, jsonObject.optJSONArray("small_topic"));

        return newTopic;
    }
}
