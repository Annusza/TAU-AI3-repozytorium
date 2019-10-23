package com.annusza.tau.lab01.myBookApp.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BookTestCase extends TestCase {

	public BookTestCase(String testName) {

		super(testName);
	}

	public static Test suite() {

		return new TestSuite(BookTestCase.class);

	}

	public void testBook() {

		assertTrue(true);
	}

}
