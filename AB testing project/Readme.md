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

|    **Evaluation matrics**    | **Standard Deviation** |
|----------------|------------|
| Gross conversion  | 0.0202 |
| Retention      | 0.05494   |
| Net conversion | 0.0156  |

calculation details can be found in the Jupyter notebook

## Choosing Number of Samples given power
with alpha = 0.05 and beta of 0.2, we can get the pageview total that adequately power the experiment are

| **Evaluation matrics** | **min detection** | **P** | **sample size** | **pagereview** |
|------------------------|-------------------|-------|-----------------|----------------|
| Gross conversion       | 0.01              | 0.206 | 25835           | 322937 |
| Retention              | 0.01              | 0.53  | 39115           | 2370606 |
| Net conversion         | 0.0075            | 0.109 | 27413           | 342662 |

sample size are caculated by [power calculator](http://www.evanmiller.org/ab-testing/sample-size.html)
## Choosing Duration vs. Exposure
- Duration for Retention matrics

total number of pagereview needed = 2370606 * 2(experiment group and control group) = 4741212

percentage of traffic 100%

total requred days = 4741212/40000 = 119 days = 17 weeks 

17 weeks Duration is too long for an experiement

- Duration for Gross conversion and Net conversion

total number of pagereview needed = 342662 * 2(experiment group and control group) = 685324 /n

percentage of traffic 100%

total requred days = 685324/40000 = 17 days 

There might be other experiment to run at the same time, so I'm expecting to have 50% of the traffic, that result in 34 days of experiment duration.

**I choose Gross conversion and Net conversion as evaluation metrics, since 34 days is a resonable duration for experiment**

## Sanity Checks
To check the invariance of matrics that has been equaly split between two groups. I construct a confidence interval for a difference in proportions, then check whether the difference between group values falls within that confidence level(alph =0.05).
The sanity check function include in the ipython notebook. 
Result:

Pagereviews sanity check - pass

clicks sanity check - pass

payments sanity check - pass

## Check for Practical and Statistical Significance

