/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.assignment.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.sakaiproject.assignment.api.AssignmentService;
import org.sakaiproject.assignment.api.model.Assignment;
import org.sakaiproject.assignment.api.model.AssignmentSubmission;
import org.sakaiproject.assignment.api.model.AssignmentSubmissionSubmitter;
import org.sakaiproject.assignment.api.sort.AssignmentSubmissionComparator;
import org.sakaiproject.assignment.api.sort.UserIdComparator;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserDirectoryService;

public class AssignmentComparatorTest {

	private Comparator<String> sortNameComparator;
	private Comparator<AssignmentSubmission> submitterNameComparator;
	private AssignmentSubmission assignmentSubmission1, assignmentSubmission2, assignmentSubmission3, assignmentSubmission4;
	private AssignmentSubmission[] namesWithSpaceSubmissions = new AssignmentSubmission[7];

	@Before
	public void setUp() throws Exception {
        AssignmentService assignmentService = Mockito.mock(AssignmentService.class);
	    UserDirectoryService userDirectoryService = Mockito.mock(UserDirectoryService.class);
		SiteService siteService = Mockito.mock(SiteService.class);

		Assignment assignment = Mockito.mock(Assignment.class);
		Mockito.when(assignment.getIsGroup()).thenReturn(false);
		
		User user1 = Mockito.mock(User.class);
		Mockito.when(user1.getSortName()).thenReturn("Muñoz");

		assignmentSubmission1 = Mockito.mock(AssignmentSubmission.class);
		Mockito.when(assignmentSubmission1.getAssignment()).thenReturn(assignment);
//		Mockito.when(assignmentService.getSubmissionSubmittersAsUsers(assignmentSubmission1)).thenReturn(new User[]{user1});

		User user2 = Mockito.mock(User.class);
		Mockito.when(user2.getSortName()).thenReturn("Muñiz");

		assignmentSubmission2 = Mockito.mock(AssignmentSubmission.class);
		Mockito.when(assignmentSubmission2.getAssignment()).thenReturn(assignment);
//		Mockito.when(assignmentSubmission2.getSubmitters()).thenReturn(new User[]{user2});
		
		User user3 = Mockito.mock(User.class);
		Mockito.when(user3.getSortName()).thenReturn("Smith");

		assignmentSubmission3 = Mockito.mock(AssignmentSubmission.class);
		Mockito.when(assignmentSubmission3.getAssignment()).thenReturn(assignment);
//		Mockito.when(assignmentSubmission3.getSubmitters()).thenReturn(new User[]{user3});

		User user4 = Mockito.mock(User.class);
		Mockito.when(user4.getSortName()).thenReturn("Adam");

		assignmentSubmission4 = Mockito.mock(AssignmentSubmission.class);
		Mockito.when(assignmentSubmission4.getAssignment()).thenReturn(assignment);
//		Mockito.when(assignmentSubmission4.getSubmitters()).thenReturn(new User[]{user4});

		Mockito.when(userDirectoryService.getUser("user1")).thenReturn(user1);
		Mockito.when(userDirectoryService.getUser("user2")).thenReturn(user2);
		Mockito.when(userDirectoryService.getUser("user3")).thenReturn(user3);
		Mockito.when(userDirectoryService.getUser("user4")).thenReturn(user4);
		Mockito.when(userDirectoryService.getUser("usernull")).thenReturn(null);

		// Adapted from UserSortNameComparatorTest in kernel
		final String USERS_WITH_SPACE_PREFIX = "usersWithSpace";
		User[] usersWithSpace = new User[7];
		for (int i = 0; i < usersWithSpace.length; i++) {
			usersWithSpace[i] = Mockito.mock(User.class);
			when(userDirectoryService.getUser(USERS_WITH_SPACE_PREFIX + i)).thenReturn(usersWithSpace[i]);
		}
		when(usersWithSpace[0].getSortName()).thenReturn("Dekfort, Apple");
		when(usersWithSpace[1].getSortName()).thenReturn("Del Fintino, Pear");
		when(usersWithSpace[2].getSortName()).thenReturn("Dekford", "Orange");
		when(usersWithSpace[3].getSortName()).thenReturn("De'Leon", "Cactus");
		when(usersWithSpace[4].getSortName()).thenReturn("Deleverde", "Mango");
		when(usersWithSpace[5].getSortName()).thenReturn("Martinez Torcal, Apple");
		when(usersWithSpace[6].getSortName()).thenReturn("Martin Troncoso, X");
		for (int i = 0; i < namesWithSpaceSubmissions.length; i++) {
			namesWithSpaceSubmissions[i] = createSubmissionForUserID(USERS_WITH_SPACE_PREFIX + i);
		}

		sortNameComparator = new UserIdComparator(userDirectoryService);
		submitterNameComparator = new AssignmentSubmissionComparator(assignmentService, siteService, userDirectoryService);
	}

	/**
	 * Create a submission object, as well as its assignment and submitters.
	 * The assignment will not be a group assignment, and the specified userID will be the only submitter.
	 */
	public static AssignmentSubmission createSubmissionForUserID(String userID)
	{
		AssignmentSubmission submission = new AssignmentSubmission();

		Assignment assignment = new Assignment();
		assignment.setIsGroup(false);
		submission.setAssignment(assignment);

		Set<AssignmentSubmissionSubmitter> submitters = new HashSet<>(1);
		AssignmentSubmissionSubmitter submitter = new AssignmentSubmissionSubmitter();
		submitter.setSubmitter(userID);
		submitters.add(submitter);
		submission.setSubmitters(submitters);

		return submission;
	}

	@Test
	public void testSortNameEQComparator() {
		// Equals results
		assertTrue(sortNameComparator.compare("user1","user1") == 0
				&& sortNameComparator.compare("user2","user2") == 0
				&& sortNameComparator.compare("user3","user3") == 0
				&& sortNameComparator.compare("user4","user4") == 0);
	}

	@Test
	public void testSortNameGTComparator() {
		// Greater than results
		assertTrue(sortNameComparator.compare("user1","user2") == 1
			&& sortNameComparator.compare("user1","user4") == 1
			&& sortNameComparator.compare("user2","user4") == 1
			&& sortNameComparator.compare("user3","user1") == 1
			&& sortNameComparator.compare("user3","user2") == 1
			&& sortNameComparator.compare("user3","user4") == 1);
	}

	@Test
	public void testSortNameLTComparator() {
		// Lower than results
		assertTrue(sortNameComparator.compare("user2","user1") == -1
			&& sortNameComparator.compare("user4","user1") == -1
			&& sortNameComparator.compare("user4","user2") == -1
			&& sortNameComparator.compare("user1","user3") == -1
			&& sortNameComparator.compare("user2","user3") == -1
			&& sortNameComparator.compare("user4","user3") == -1);
	}

	// TODO fix tests
	@Ignore
	@Test
	public void testSubmitterNameEQComparator() {
		// Equals results
		assertTrue(submitterNameComparator.compare(assignmentSubmission1,assignmentSubmission1) == 0 
				&& submitterNameComparator.compare(assignmentSubmission2,assignmentSubmission2) == 0
				&& submitterNameComparator.compare(assignmentSubmission3,assignmentSubmission3) == 0
				&& submitterNameComparator.compare(assignmentSubmission4,assignmentSubmission4) == 0);
	}

	@Ignore
	@Test
	public void testSubmitterNameGTComparator() {
		// Greater than results
		assertTrue(submitterNameComparator.compare(assignmentSubmission1,assignmentSubmission2) == 1
			&& submitterNameComparator.compare(assignmentSubmission1,assignmentSubmission4) == 1
			&& submitterNameComparator.compare(assignmentSubmission2,assignmentSubmission4) == 1
			&& submitterNameComparator.compare(assignmentSubmission3,assignmentSubmission1) == 1
			&& submitterNameComparator.compare(assignmentSubmission3,assignmentSubmission2) == 1
			&& submitterNameComparator.compare(assignmentSubmission3,assignmentSubmission4) == 1);
	}

	@Ignore
	@Test
	public void testSubmitterNameLTComparator() {
		// Lower than results
		assertTrue(submitterNameComparator.compare(assignmentSubmission2,assignmentSubmission1) == -1
			&& submitterNameComparator.compare(assignmentSubmission4,assignmentSubmission1) == -1
			&& submitterNameComparator.compare(assignmentSubmission4,assignmentSubmission2) == -1
			&& submitterNameComparator.compare(assignmentSubmission1,assignmentSubmission3) == -1
			&& submitterNameComparator.compare(assignmentSubmission2,assignmentSubmission3) == -1
			&& submitterNameComparator.compare(assignmentSubmission4,assignmentSubmission3) == -1);
	}

	@Test
	public void testSubmitterNamesWithSpaces() {
		// Adapted from UserSortNameComparatorTest in kernel
		assertEquals(-1, submitterNameComparator.compare(namesWithSpaceSubmissions[0], namesWithSpaceSubmissions[1]));
		assertEquals(1, submitterNameComparator.compare(namesWithSpaceSubmissions[1], namesWithSpaceSubmissions[0]));
		assertEquals(0, submitterNameComparator.compare(namesWithSpaceSubmissions[1], namesWithSpaceSubmissions[1]));
		assertEquals(-1, submitterNameComparator.compare(namesWithSpaceSubmissions[3], namesWithSpaceSubmissions[2]));
		assertEquals(-1, submitterNameComparator.compare(namesWithSpaceSubmissions[3], namesWithSpaceSubmissions[4]));
		assertEquals(1, submitterNameComparator.compare(namesWithSpaceSubmissions[5], namesWithSpaceSubmissions[6]));
	}

}
