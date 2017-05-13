package rohan.dreamteam.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import rohan.dreamteam.exceptions.DreamTeamException;
import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;

@Service
public class DataConverterImpl implements DataConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataConverterImpl.class);

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public InitialDataRoot convertJsonToInitialDataRoot(final String json) {

		InitialDataRoot initialDataRoot = null;

		try {
			prettyPrint(json);
			initialDataRoot = MAPPER.readValue(json, InitialDataRoot.class);
		} catch (IOException e) {
			final String exceptionMessage = "Exception converting JSON to InitialDataRoot";
			LOGGER.error(exceptionMessage, e);
			throw new DreamTeamException(exceptionMessage);
		}

		return initialDataRoot;
	}

	@Override
	public PlayerDataRoot convertJsonToPlayerDataRoot(final String json) {

		PlayerDataRoot playerDataRoot = null;

		try {
			prettyPrint(json);
			playerDataRoot = MAPPER.readValue(json, PlayerDataRoot.class);
		} catch (IOException e) {
			final String exceptionMessage = "Exception converting JSON to PlayerDataRoot";
			LOGGER.error(exceptionMessage, e);
			throw new DreamTeamException(exceptionMessage);
		}

		return playerDataRoot;
	}

	private void prettyPrint(final String json) {
		try {
			if (LOGGER.isTraceEnabled()) {
				Object prettyPlayerData = MAPPER.readValue(json, Object.class);
				LOGGER.trace(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(prettyPlayerData));
			}
		} catch (IOException e) {
			final String exceptionMessage = "Exception printing JSON";
			LOGGER.error(exceptionMessage, e);
			throw new DreamTeamException(exceptionMessage);
		}
	}

}
