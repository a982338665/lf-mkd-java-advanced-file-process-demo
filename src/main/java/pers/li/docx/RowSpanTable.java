package pers.li.docx;
/**
 * 本类完成（合并单元格）的表格
 */

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;


public class RowSpanTable {

    public static void main(String[] args) {

        int rows = 5;
        int cols = 5;

        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable(rows, cols);

        fillTable(table);

        mergeCellsVertically(table, 3, 1, 3);

        try {
            FileOutputStream out = new FileOutputStream("./rowSpanTable.docx");
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void fillTable(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);

            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                cell.addParagraph().createRun().setText(" cell " + rowIndex + colIndex + " ");
            }
        }
    }

    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {

        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {

            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);

            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

}
