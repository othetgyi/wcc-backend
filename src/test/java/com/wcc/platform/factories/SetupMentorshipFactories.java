package com.wcc.platform.factories;

import static com.wcc.platform.factories.SetUpStyleFactories.createCustomStyleTest;
import static com.wcc.platform.factories.SetupFactories.OBJECT_MAPPER;
import static com.wcc.platform.factories.SetupFactories.createCommonSectionTest;
import static com.wcc.platform.factories.SetupFactories.createContactTest;
import static com.wcc.platform.factories.SetupFactories.createLinkTest;
import static com.wcc.platform.factories.SetupFactories.createListSectionTest;
import static com.wcc.platform.factories.SetupFactories.createNoImageHeroSectionTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wcc.platform.domain.cms.PageType;
import com.wcc.platform.domain.cms.attributes.CommonSection;
import com.wcc.platform.domain.cms.attributes.FaqItem;
import com.wcc.platform.domain.cms.attributes.ListSection;
import com.wcc.platform.domain.cms.attributes.Mentor;
import com.wcc.platform.domain.cms.attributes.Participants;
import com.wcc.platform.domain.cms.pages.mentorship.FeedbackItem;
import com.wcc.platform.domain.cms.pages.mentorship.FeedbackSection;
import com.wcc.platform.domain.cms.pages.mentorship.MentorshipCodeOfConductPage;
import com.wcc.platform.domain.cms.pages.mentorship.MentorshipFaqPage;
import com.wcc.platform.domain.cms.pages.mentorship.MentorshipPage;
import com.wcc.platform.domain.cms.pages.mentorship.MentorshipStudyGroupPage;
import com.wcc.platform.domain.cms.pages.mentorship.StudyGroupSection;
import com.wcc.platform.domain.platform.MemberType;
import com.wcc.platform.domain.platform.StudyGroup;
import com.wcc.platform.utils.FileUtil;
import java.time.Year;
import java.util.List;

/** Mentorship test factories. */
public class SetupMentorshipFactories {

  /** Test factory. */
  public static MentorshipPage createMentorshipPageTest(final String fileName) {
    try {
      final String content = FileUtil.readFileAsString(fileName);
      return OBJECT_MAPPER.readValue(content, MentorshipPage.class);
    } catch (JsonProcessingException e) {
      return createMentorshipPageTest();
    }
  }

  /** Test factory. */
  public static MentorshipPage createMentorshipPageTest() {
    final String pageId = PageType.MENTORSHIP.getId();
    return new MentorshipPage(
        pageId,
        createNoImageHeroSectionTest(),
        createCommonSectionTest(),
        createListSectionTest("Mentor"),
        createListSectionTest("Mentee"),
        createFeedbackSectionTest());
  }

  public static FeedbackItem createFeedbackItemTest(final MemberType memberType) {
    return new FeedbackItem("Person Name", "Nice feedback", memberType, Year.of(2023));
  }

  /**
   * Factory test for FeedbackSection class.
   *
   * @return FeedbackSection
   */
  public static FeedbackSection createFeedbackSectionTest() {
    return new FeedbackSection(
        "Feedback1",
        List.of(
            createFeedbackItemTest(MemberType.MENTOR), createFeedbackItemTest(MemberType.MENTEE)));
  }

  public static MentorshipFaqPage createMentorshipFaqPageTest(final String fileName) {
    try {
      final String content = FileUtil.readFileAsString(fileName);
      return OBJECT_MAPPER.readValue(content, MentorshipFaqPage.class);
    } catch (JsonProcessingException e) {
      return createMentorshipFaqPageTest();
    }
  }

  public static MentorshipFaqPage createMentorshipFaqPageTest() {
    final String pageId = PageType.MENTORSHIP_FAQ.getId();
    return new MentorshipFaqPage(
        pageId,
        createNoImageHeroSectionTest(),
        createListFaqSectionTest(new FaqItem("Common", "Common FAQ")),
        createListFaqSectionTest(new FaqItem("Mentor", "Mentor FAQ")),
        createListFaqSectionTest(new FaqItem("Mentee", "Mentee FAQ")));
  }

  private static ListSection<FaqItem> createListFaqSectionTest(final FaqItem faqItem) {
    return new ListSection<>("FAQ", null, null, List.of(faqItem));
  }

  public static MentorshipCodeOfConductPage createMentorshipConductPageTest(final String fileName) {
    try {
      final String content = FileUtil.readFileAsString(fileName);
      return OBJECT_MAPPER.readValue(content, MentorshipCodeOfConductPage.class);
    } catch (JsonProcessingException e) {
      return createMentorshipConductPageTest();
    }
  }

  public static MentorshipCodeOfConductPage createMentorshipConductPageTest() {
    final String pageId = PageType.MENTORSHIP_CONDUCT.getId();
    return new MentorshipCodeOfConductPage(
        pageId,
        createNoImageHeroSectionTest(),
        createListSectionTest("Mentee Code of Conduct"),
        createListSectionTest("Mentor Code of Conduct"),
        createCommonSectionOnlyLinkTest());
  }

  private static CommonSection createCommonSectionOnlyLinkTest() {
    return new CommonSection(null, null, null, createLinkTest(), null, null);
  }

  /** Factory test for StudyGroupSection. */
  private static StudyGroupSection createStudyGroupSectionTest(
      final String title, final List<StudyGroup> studyGroups) {
    return new StudyGroupSection(
        title != null ? title : "Default Title", studyGroups != null ? studyGroups : List.of());
  }

  /** Test factory for Study Group Page */
  public static MentorshipStudyGroupPage createMentorshipStudyGroupPageTest(final String fileName) {
    try {
      final String content = FileUtil.readFileAsString(fileName);
      return OBJECT_MAPPER.readValue(content, MentorshipStudyGroupPage.class);
    } catch (JsonProcessingException e) {
      return createMentorshipStudyGroupPageTest();
    }
  }

  /** Test factory for StudyGroupPage. */
  public static MentorshipStudyGroupPage createMentorshipStudyGroupPageTest() {
    final String pageId = PageType.STUDY_GROUPS.getId();
    StudyGroup studyGroup =
        new StudyGroup(
            "group-1",
            "Study Group 1",
            new Mentor("mentor", "mentor name", "www.test-mentor-link.com"),
            new Participants("participants", 1));

    return new MentorshipStudyGroupPage(
        pageId,
        createNoImageHeroSectionTest(),
        createCommonSectionTest(),
        createContactTest(),
        createStudyGroupSectionTest("Study Group", List.of(studyGroup)),
        createCustomStyleTest());
  }
}
