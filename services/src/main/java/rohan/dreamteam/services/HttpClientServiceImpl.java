package rohan.dreamteam.services;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import rohan.dreamteam.exceptions.DreamTeamException;

@Service
public class HttpClientServiceImpl implements HttpClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientServiceImpl.class);

	@Override
	public String getDataString(final String url) {
		HttpClient client = new HttpClient();

		HttpMethod method = new GetMethod(url);

		String responseBody = null;
		try {
			client.executeMethod(method);
			responseBody = method.getResponseBodyAsString();
		} catch (IOException e) {
			final String exceptionMessage = "Exception calling " + url;
			LOGGER.error(exceptionMessage, e);
			throw new DreamTeamException(exceptionMessage);
		}

		return responseBody;
	}

}
