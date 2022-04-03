package telran.b7a.puzzleGames.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import telran.b7a.puzzleGames.dao.PlayerRepository;
import telran.b7a.puzzleGames.dao.PuzzleRepository;
import telran.b7a.puzzleGames.dao.PuzzleResultsRepository;
import telran.b7a.puzzleGames.dto.AnswersDto;
import telran.b7a.puzzleGames.exceptions.PlayerNotFoundException;
import telran.b7a.puzzleGames.exceptions.PuzzleNotFoundException;
import telran.b7a.puzzleGames.exceptions.PuzzleResultNotFoundException;
import telran.b7a.puzzleGames.models.Player;
import telran.b7a.puzzleGames.models.Puzzle;
import telran.b7a.puzzleGames.models.PuzzleResult;

@Service
public class PuzzleServiceImpl implements PuzzleService {

	PuzzleRepository puzzleRepository;
	PlayerRepository playerRepository;
	PuzzleResultsRepository puzzleResultsRepository;
	JavaMailSender mailSender;
	SimpleMailMessage templateMessage;

	@Autowired
	public PuzzleServiceImpl(PuzzleRepository puzzleRepository, PlayerRepository playerRepository,
			PuzzleResultsRepository puzzleResultsRepository, SimpleMailMessage templateMessage,
			JavaMailSender mailSender) {
		this.puzzleRepository = puzzleRepository;
		this.playerRepository = playerRepository;
		this.puzzleResultsRepository = puzzleResultsRepository;
		this.templateMessage = templateMessage;
		this.mailSender = mailSender;

	}

	@Override
	public Puzzle createPuzzle(Integer numOfItems) {
		Puzzle puzzle = new Puzzle(numOfItems);
		return puzzleRepository.save(puzzle);
	}

	@Override
	public Puzzle getPuzzle(Integer puzzleId) {
		return findPuzzle(puzzleId);
	}

	@Override
	public Puzzle udpdatePuzzle(Integer puzzleId, Integer numOfItems) {
		Puzzle puzzle = findPuzzle(puzzleId);
		puzzle.setNumOfItems(numOfItems);
		puzzleRepository.save(puzzle);
		return puzzle;
	}

	@Override
	public void removePuzzle(Integer puzzleId) {
		Puzzle puzzle = findPuzzle(puzzleId);
		puzzleRepository.delete(puzzle);

	}

	@Override
	public PuzzleResult postResult(Integer playerId, Integer puzzleId, AnswersDto result) {
		Puzzle puzzle = findPuzzle(puzzleId);
		PuzzleResult puzzleResult = new PuzzleResult(new HashSet<>());
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
		if (result.getFoundItems().size() != puzzle.getNumOfItems()) {
			Set<Integer> responseSet = new HashSet<>();
			for (int i = 1; i <= puzzle.getNumOfItems(); i++) {
				responseSet.add(i);
			}
			responseSet.removeAll(result.getFoundItems());
			puzzleResult.setMissingItems(responseSet);
		}
		puzzleResult.setPlayer(player);
//		sendEmail("nikan143@yandex.ru", result.toString(), "result.txt");
//		sendEmailWithAttachment("nikan143@yandex.ru", "your result", "your result in attachment", "result.txt");
		return puzzleResultsRepository.save(puzzleResult);
	}

	private void sendEmailWithAttachment(String email, String subject, String text, String fileName) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
				mimeMessage.setFrom(new InternetAddress("nikan2110@gmail.com"));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(text);

				FileSystemResource file = new FileSystemResource(new File(fileName));
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.addAttachment("result.txt", file);
				helper.setText("", true);

			}
		};
		mailSender.send(preparator);

	}

	private void sendEmail(String email, String text, String file) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
		mailMessage.setTo(email);
		mailMessage.setText(text);
		mailSender.send(mailMessage);

	}

	@Override
	public PuzzleResult getResult(Integer resultId) {
		return puzzleResultsRepository.findById(resultId)
				.orElseThrow(() -> new PuzzleResultNotFoundException(resultId));
	}

	@Override
	public List<PuzzleResult> getResults() {
		return puzzleResultsRepository.findAll();
	}

	@Scheduled(cron = "0 30 17 * * *")
	public void saveResults() {
		try (FileOutputStream fileOut = new FileOutputStream("resultsS.txt")) {
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(puzzleResultsRepository.findAll());
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	@Override
	public PuzzleResult saveResultInFile(Integer puzzleResultId) {
		PuzzleResult result = puzzleResultsRepository.findById(puzzleResultId)
				.orElseThrow(() -> new PuzzleResultNotFoundException(puzzleResultId));
		try (FileWriter fileWriter = new FileWriter("result.txt")) {
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(result.toString());
		} catch (Exception err) {
			err.printStackTrace();
		}
		return result;

	}

	private Puzzle findPuzzle(Integer puzzleId) {
		return puzzleRepository.findById(puzzleId).orElseThrow(() -> new PuzzleNotFoundException(puzzleId));
	}

}
