import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

/**
 * Created by ASUS on 2016/7/27.
 */
public class DeleteRepeat extends WriteCommandAction.Simple {
    private Document document;
    private Project project;
    private String newDocContent;
    protected DeleteRepeat(Project project,  Document doc, String newDocContetn, PsiFile... files) {
        super(project, files);
        this.document = doc;
        this.project  = project;
        this.newDocContent = newDocContetn;
    }

    @Override
    protected void run() throws Throwable {
        document.setText(newDocContent);
        PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(project);
        psiDocumentManager.commitDocument(document);
    }
}
