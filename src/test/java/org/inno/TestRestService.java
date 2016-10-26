package org.inno;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class TestRestService extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
 
	@Test
	public void testStringInsertSuccess() throws IOException,
			URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/");
		Integer returnObject = webResource.path("/register/rest/service/albert")
				.post(Integer.class);
		assertEquals("1152", Integer.toString(returnObject));
	}
 
	@Test(expected = UniformInterfaceException.class)
	public void testStringInsertDuplicate() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Integer returnObject = webResource.path("/register/rest/service/albert")
				.post(Integer.class);
	}

	@Test(expected = UniformInterfaceException.class)
	public void testStringWrongInsertFailure() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Integer returnObject = webResource.path("/register/rest/service/albert")
				.post(Integer.class);
	}

	@Test(expected = UniformInterfaceException.class)
	public void testFetchListWithStringID() {
		WebResource webResource = client().resource("http://localhost:8080/");
		List<String> returnObject = webResource.path("/register/rest/service/albert")
				.get(new GenericType<List<String>>(){});
		Assert.assertNotNull(returnObject);
	}

}
