# A/B Testing - Effectivess of interventions to improve Udacity courses completion rate

## Experient Overview
At the time of this experiment, Udacity courses currently have two options on the home page: "start free trial", 
and "access course materials". If the student clicks "start free trial", they will be asked to enter their credit card information, 
and then they will be enrolled in a free trial for the paid version of the course. After 14 days, they will automatically be charged 
unless they cancel first. If the student clicks "access course materials", they will be able to view the videos and take the quizzes for 
free, but they will not receive coaching support or a verified certificate, and they will not submit their final project for feedback.
In the experiment, Udacity tested a change where if the student clicked "start free trial", they were asked how much time they had 
available to devote to the course. If the student indicated 5 or more hours per week, they would be taken through the checkout process 
as usual. If they indicated fewer than 5 hours per week, a message would appear indicating that Udacity courses usually require a greater 
time commitment for successful completion, and suggesting that the student might like to access the course materials for free. At this 
point, the student would have the option to continue enrolling in the free trial, or access the course materials for free instead.
hypothesis was that this might set clearer expectations for students upfront, thus reducing the number of frustrated students who left 
the free trial because they didn't have enough time—without significantly reducing the number of students to continue past the free 
trial and eventually complete the course. If this hypothesis held true, Udacity could improve the overall student experience and 
improve coaches' capacity to support students who are likely to complete the course.
![](https://drive.google.com/file/d/0ByAfiG8HpNUMakVrS0s4cGN2TjQ/view)

## Metric Choice

### Invariant Variables: 
Invariant metrics were chosed with expectation to have similar distributions for control and experiment groups. It is a required 
sanity check process before evaluate on the result 
- Number of cookies: The unit of division if cookie, number of unique cookies to view the course overview page should be invariant between control and experiment group
- Number of clicks: Cliking on 'start Free Trial' happens before the screener, it is expected to be invariant to start with
- Click through probability: click through probability is clicks divided by cookies. Since cookies and clicks are invariant, click through probability should also be invariant

### Evaluation Metrics:
Evaluation metrics decide on what to test on to determine if there is a difference between control and experiment groups. In this case, to determin the effectiness of the screener,
we need either more students stay beyond free trial period (retention) or more students stay beyond free trial with less student enroll in the free trial (reduce Gross Conversion, incress Net Conversion)
- Gross Conversion: That is, number of user-ids to complete checkout and enroll in the free trial divided by number of unique cookies to click the "Start free trial" button.
- Retention: That is, number of user-ids to remain enrolled past the 14-day boundary (and thus make at least one payment) divided by number of user-ids to complete checkout.
- Net conversion: That is, number of user-ids to remain enrolled past the 14-day boundary (and thus make at least one payment) divided by the number of unique cookies to click the "Start free trial" button. 

## Measuring Variability
