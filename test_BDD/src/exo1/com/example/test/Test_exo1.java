package exo1.com.example.test;

import com.example.model.Matrix;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test_exo1 {
    double [][] mat = new double[3][3];
    com.example.model.Matrix trans;
    com.example.model.Matrix expectedtrans;
    double det ;
    @Given("i have a matrix")
    public void iHaveAMatrix(DataTable table) {
        List<Map<Object, Object>> rows = table.asMaps(String.class, String.class);
        int i = 0 ;
        for (Map<Object, Object> columns : rows) {
            mat[i][0] = Double.parseDouble((String) columns.get("col1"));
            mat[i][1] = Double.parseDouble((String) columns.get("col2"));
            mat[i][2] = Double.parseDouble((String) columns.get("col3"));
            i++;
        }


    }
    @When("i calculate the det")
    public void iCalculateTheDet() {
        com.example.model.Matrix m = new com.example.model.Matrix();
        m.setData(mat);
        try {
            det = exo1.com.example.service.MatrixMathematics.determinant(m);
            System.out.print(det);
        }catch(Exception e){
            System.out.print(e);
        }

    }

    @Then("i should have {int}")
    public void iShouldHave(int arg0) {
        assertEquals(20,det,0);
    }


    @When("i calculate the the traspose")
    public void iCalculateTheTheTraspose() {
        com.example.model.Matrix m = new com.example.model.Matrix();
        m.setData(mat);
        trans = exo1.com.example.service.MatrixMathematics.transpose(m);;

    }

    @Then("i should have")
    public void iShouldHave(DataTable table) {
        List<Map<Object, Object>> rows = table.asMaps(String.class, String.class);
        int i = 0 ;
        for (Map<Object, Object> columns : rows) {
            mat[i][0] = Double.parseDouble((String) columns.get("col1"));
            mat[i][1] = Double.parseDouble((String) columns.get("col2"));
            mat[i][2] = Double.parseDouble((String) columns.get("col3"));
            i++;
        }
        for (int j = 0; j < 3; j++) {
            assertEquals(trans.getValueAt(j,0),mat[j][0],0);
            assertEquals(trans.getValueAt(j,1),mat[j][1],0);
            assertEquals(trans.getValueAt(j,2),mat[j][2],0);
        }
    }
}
