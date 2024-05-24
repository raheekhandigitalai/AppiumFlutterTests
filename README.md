## Appium Flutter Test Examples

This repository is meant to be used for running Flutter based Appium Tests and provides examples of simple tests.

### Setup

#1 Provide your Cloud URL and Access Key in config.properties file.

Cloud URL Example
ct.cloudUrl=https://uscloud.experitest.com

[(Documentation) Obtain your Access Key](https://docs.digital.ai/bundle/TE/page/obtaining_access_key.html)

#2 Running the Tests

The Framework uses Maven, to run the test(s), run the following command from the Terminal

```agsl
mvn clean test
```