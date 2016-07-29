import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtilBase;
import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2016/7/26.
 */
public class NorepeatMani extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //Messages.showMessageDialog("nothing","title",Messages.getInformationIcon());
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        PsiFile file  = file = PsiUtilBase.getPsiFileInEditor(editor, project);
        Document doc = editor.getDocument();
        if(doc!=null){
            String text = doc.getText();
            //parse text to dom4j document object
            try {
                org.dom4j.Document xmlDoc = DocumentHelper.parseText(text);
                Element rootEle = xmlDoc.getRootElement();
                List<Element> nodes = rootEle.elements("uses-permission");
                Map<String,Object> map = new HashMap<String,Object>();
                for (Element ele : nodes){
                    Attribute attr = ele.attribute("name");
                    String name = attr.getText();
                    if(map.containsKey(name)){
                        rootEle.remove(ele);
                    }else{
                        map.put(name,ele);
                    }
                }
                String xmlStr = xmlDoc.asXML();
                new DeleteRepeat(project,doc,xmlStr,file).execute();
                Messages.showMessageDialog("ok","title",Messages.getInformationIcon());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{
            Messages.showMessageDialog("no doc","title",Messages.getErrorIcon());
        }
    }

}
