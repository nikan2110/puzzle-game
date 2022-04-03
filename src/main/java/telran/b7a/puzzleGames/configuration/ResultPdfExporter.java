package telran.b7a.puzzleGames.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import telran.b7a.puzzleGames.models.PuzzleResult;

public class ResultPdfExporter {

	List<PuzzleResult> puzzleResults;

	public ResultPdfExporter(List<PuzzleResult> puzzleResults) {
		this.puzzleResults = puzzleResults;
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("Result id"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Player id"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Player name"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Player birthDate"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Missing items"));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (PuzzleResult puzzleResult : puzzleResults) {
			table.addCell(String.valueOf(puzzleResult.getId()));
			table.addCell(String.valueOf(puzzleResult.getPlayer().getId()));
			table.addCell(puzzleResult.getPlayer().getName());
			table.addCell(puzzleResult.getPlayer().getBirthDate().toString());
			table.addCell(puzzleResult.getMissingItems().toString());
		}
	}

}
