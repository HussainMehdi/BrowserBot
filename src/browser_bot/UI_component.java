/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;

import com.google.common.collect.Lists;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Madan Lal
 */
public class UI_component 
{
    //This contatins all UI Componants whose Clickable==true
    public boolean Traversed=false;
    private String Text;
    private String ClassName;
    private String PackageName;
    private String Description;
    private String Checkable;
    private String Clickable;
    private String Enabled;
    private String Focusable;
    private String Focused;
    private String Scrollable;
    private String LongClickable;
    private String Selected;
    private String ResourceId;
    private String InstanceId;
    
    coordinates coordinates_bounds;
    
    public String getText()
    {
        
        return Text;
    }
    public String getClassName()
    {
        return ClassName;
    }
    
    public String getPackageName()
    {
        return PackageName;
    }
    
    public String getDescription()
    {
        return Description;
    }
    
    public String getCheckable()
    {
        return Checkable;
    }
    
    public String getClickable()
    {
        return Clickable;
    }
    
    public String getEnabled()
    {
        return Enabled;
    }
    
    public String getFocusable()
    {
        return Focusable;
    }
    
    public String getFocused()
    {
        return Focused;
    }
    
    public String getScrollable()
    {
        return Scrollable;
    }
    
    public String getLongClickable()
    {
        return LongClickable;
    }
    
    public String getSelected()
    {
        return Selected;
    }
    
    public String getResourceId()
    {
        return ResourceId;
    }
    
    public String getInstanceId()
    {
        return InstanceId;
    }
    
    public coordinates getCoordinates()
    {
         return coordinates_bounds;
    } 
    
    public void setText(String _Text)
    {
        Text=_Text;
    }
    
    public void setClassName(String _ClassName)
    {
       ClassName=_ClassName;
    }
    
    public void setPackageName(String _PackageName)
    {
       PackageName=_PackageName;
    }
    
    public void setDescription(String _Description)
    {
       Description=_Description;
    }
    
    public void setCheckable(String _Checkable)
    {
       Checkable=_Checkable;
    }
    
    public void setClickable(String _Clickable)
    {
        Clickable=_Clickable;
    }
    
    public void setEnabled(String _Enabled)
    {
       Enabled=_Enabled;
    }
    
    public void setFocusable(String _Focusable)
    {
       Focusable=_Focusable;
    }
    
    public void setFocused(String _Focused)
    {
       Focused=_Focused;
    }
    
    public void setScrollable(String _Scrollable)
    {
       Scrollable=_Scrollable;
    }
    
    public void setLongClickable(String _LongClickable)
    {
       LongClickable=_LongClickable;
    }
    
    public void setSelected(String _Selected)
    {
       Selected=_Selected;
    }
    
    public void setResourceId(String _ResourceId)
    {
       ResourceId=_ResourceId;
    }
    
    public void setInstanceId(String _InstanceId)
    {
       InstanceId=_InstanceId;
    }
 
    public void setCoordinates(coordinates _coordinates_bounds)
    {
        coordinates_bounds=_coordinates_bounds;
    }
    
    public static List<UI_component> Prioritize( List<UI_component> uicomps)
    {
        System.out.println("Prioritizing UIComponents !!");
        List<UI_component> ListCopy = new ArrayList<>(uicomps);
        List<UI_component> ButtonList =  new ArrayList<>();
        List<UI_component> ResourceIDList =  new ArrayList<>();
        
        
        for(UI_component uicomp: uicomps)
        {
            if(uicomp.getResourceId().length()>1)
            {
                ResourceIDList.add(uicomp);
                ListCopy.remove(uicomp);
                if(uicomp.ClassName.equals("android.widget.Button"))
                    ButtonList.add(uicomp);
            }
            else 
            if (uicomp.ClassName.equals("android.widget.Button"))
            {
                ButtonList.add(uicomp);
                ListCopy.remove(uicomp);
            }
            
        }
        List<UI_component> ButtonCopyList=new ArrayList(ButtonList);
        
        for(UI_component uicomp: ButtonCopyList)
        {
            if (
                 uicomp.Text.toLowerCase().contains("yes")         ||
                 uicomp.Text.toLowerCase().contains("agree")       ||
                 uicomp.Text.toLowerCase().contains("approve")     ||
                 uicomp.Text.toLowerCase().contains("accept")      ||
                 uicomp.Text.toLowerCase().contains("sure")        || 
                 uicomp.Text.toLowerCase().contains("absolute")    ||   
                 uicomp.Text.toLowerCase().contains("affirmative") ||
                 uicomp.Text.toLowerCase().contains("indeed")
               )
            {
                UI_component AddAgain = uicomp;
                ButtonList.remove(uicomp);
                ButtonList.add(AddAgain);
            }
        }
        ButtonList = Lists.reverse(ButtonList);
        
        /*for(UI_component uicomp: NewList)
        {
            System.out.println("id: "+uicomp.ResourceId+" Text: "+uicomp.Text);
        }
        */
        /*for(UI_component uicomp: ButtonList)
        {
            //if (!L.contains(uicomp))
            ListCopy.add(uicomp);
        }*/
        ListCopy.addAll(ResourceIDList);
        ListCopy = Lists.reverse(ListCopy);
        ListCopy.addAll(ButtonList);
        
        return ListCopy;
    }
}

