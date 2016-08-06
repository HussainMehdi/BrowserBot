/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Madan Lal
 */
public class Layout_UI_Scrapper 
{
    public Stack _stack=new Stack();
    public int id=0;

    private List<UI_component> ListOfClickableElements=new ArrayList<UI_component>();
    
//XML Parsing
   public List<UI_component> XML_parser(String xml_string) throws ParserConfigurationException, SAXException, IOException
   {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	InputSource is = new InputSource(new StringReader(xml_string));
        org.w3c.dom.Document doc = dBuilder.parse(is);
       
        Element root=doc.getDocumentElement();//get the root element from XML
        
        Map<String,String> component_details=new HashMap<String, String>(); //<Key,Value> pair of the attributes of each component. Like <index,0>, <clickable,true> etc.
        
        ExtractAttributesAndValues(root.getChildNodes(), component_details);
        
        return ListOfClickableElements;
   }
   
   
   void ExtractAttributesAndValues(NodeList nodeList, Map<String,String> component_details)
   {
        for (int i = 0; i < nodeList.getLength(); i++)
        { //Traverse each XML element
            try
            {
                Node childNode = nodeList.item(i);
                NamedNodeMap attributes= childNode.getAttributes();
           
                for (int a = 0; a < attributes.getLength(); a++) 
                {
                    Node theAttribute = attributes.item(a);
                    component_details.put(theAttribute.getNodeName(), theAttribute.getNodeValue());
                }
                filter_clickables(component_details); //Only save clickable=true elements
            
                // Record the node data, PLUS SOME unique application instance id + activity name + ????? [bounds]
                // TODO: Log(childNode);
                
                NodeList children = childNode.getChildNodes();
                if (children != null)
                {
                    ExtractAttributesAndValues(children, component_details); //Because of heirarchy in XML, using recursion
                }
            }
            catch (Exception e)
            {
            }
        }
    }
   
   private void filter_clickables(Map<String, String> componet_details)
   {
    
       //if(componet_details.get("clickable").equals("true"))
       {
           UI_component current_component=new UI_component();
           
           
           current_component.setResourceId(componet_details.get("resource-id"));
           current_component.setText(componet_details.get("text"));
           current_component.setClassName(componet_details.get("class"));
           current_component.setCheckable(componet_details.get("checkable"));
           current_component.setDescription(componet_details.get("content-desc"));
           current_component.setEnabled(componet_details.get("enabled"));
    
           current_component.setClickable(componet_details.get("clickable"));
           current_component.setFocusable(componet_details.get("focusable"));
           current_component.setFocused(componet_details.get("focused"));
           current_component.setLongClickable(componet_details.get("long-clickable"));
           current_component.setPackageName(componet_details.get("package"));;
           current_component.setScrollable(componet_details.get("scrollable"));
           current_component.setSelected(componet_details.get("selected"));
           current_component.setInstanceId(componet_details.get("instance"));
           
           String[] bounds=filterBounds(componet_details); //Filter XY of elements from XML format [x1,y1][x2,y2]
           coordinates coordinates_bounds=new coordinates(Integer.parseInt(bounds[0]) , Integer.parseInt(bounds[1]), Integer.parseInt(bounds[2]), Integer.parseInt(bounds[3]));
           
           current_component.setCoordinates(coordinates_bounds);
           
          // System.out.println("1:"+coordinates_bounds.start_x+"\t2:"+coordinates_bounds.start_y+"\t3:"+coordinates_bounds.end_x+"\t4:"+coordinates_bounds.end_y);
          
           ListOfClickableElements.add(current_component);
           
         //  System.out.println("DESC:"+current_component.getDescription());
       }
   }
   public String[] filterBounds(Map<String,String> componet_details)
   {
           String bounds=componet_details.get("bounds");
           String[] bounds_arr  =   bounds.split("]");
           bounds_arr[0]= bounds_arr[0].replace("[", "");
           bounds_arr[1]= bounds_arr[1].replace("[", "");
           String[] startxy, endxy;
           startxy=bounds_arr[0].split(",");
           endxy=bounds_arr[1].split(",");
           
           String[] final_bounds=new String[4];
           final_bounds[0]=startxy[0];
           final_bounds[1]=startxy[1];
           final_bounds[2]=endxy[0];
           final_bounds[3]=endxy[1];
           
           return final_bounds;
                   
   }
   private void UIElementLogger()
   {
       
       // applicationPackageId
       // activityName
       // ActivityInstance: ????? [bounds]
       // index="0"
       // text=""
       // class="android.widget.FrameLayout"
       // package="com.moveloot.movelootapp"
       // content-desc=""
       // checkable="false"
       // checked="false"
       // clickable="false"
       // enabled="true"
       // focusable="false"
       // focused="false"
       // scrollable="false"
       // long-clickable="false"
       // password="false"
       // selected="false"
       // bounds="[0,75][1080,1920]"
       // resource-id=""
       // instance="2"
       
   }
}