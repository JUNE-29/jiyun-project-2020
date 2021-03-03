package june.project.util;

import java.util.HashMap;

public class RequestMappingHandlerMapping {

  HashMap<String, RequestHandler> handlerMap = new HashMap<>();

  public void addHandler(String name, RequestHandler requestHandeler) {
    handlerMap.put(name, requestHandeler);
  }

  public RequestHandler getHandler(String name) {
    return handlerMap.get(name);
  }
}
