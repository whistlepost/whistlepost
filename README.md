# Whistlepost - Digital content authoring and publishing

[separation of concerns]: https://en.wikipedia.org/wiki/Separation_of_concerns

[Apache Sling]: http://sling.apache.org/
[template engines]: https://sling.apache.org/documentation/bundles/scripting.html
[content management]: https://sling.apache.org/documentation/bundles.html#resource-providers

[Jekyll]: https://jekyllrb.com/
[Wordpress]: https://wordpress.org/
[Adobe Experience Manager]: https://docs.adobe.com/

[Webpack]: https://webpack.github.io
[Docker]: https://www.docker.com

[Lazybones]: https://github.com/pledbrook/lazybones
[SDKMAN]: http://sdkman.io/

[Google Analytics]: https://analytics.google.com/analytics
[Disqus]: https://disqus.com
[JSON-LD]: https://json-ld.org
[Opengraph]: http://ogp.me


[Introduction]: #introduction

[Features]: #features

[Getting Started]: #getting-started

[Extensions]: #extensions


#### Table of Contents

1. [Introduction - What is Whistlepost?][Introduction]
2. [Features][Features]
2. [Getting Started][Getting Started]
2. [Extensions][Extensions]

## Introduction

Whistlepost is a templating engine for Web sites, that helps to manage site structure and content independently of the presentation. Often
we think of modularity and encapsulation as important design principles for software that is more secure, reliable and easier to maintain.
This is often expressed as a [separation of concerns], and is the basis for the Whistlepost platform.

### Apache Sling

Whistlepost is built on [Apache Sling], which has a unique approach to content management in that it provides separation of not only the presentation and content but also the content structure itself. This helps to coneptualise the relationship between content without being distracted by the presentation.

Whilst Whistlepost shares the same basis as [Adobe Experience Manager], it's focus is more aligned to the popular [Jekyll] template engine, which is more concerned with the content rendering than lifecycle management. Whistlepost is designed to make it easy to "drop in" to a new or existing site to assist with the separation of the UI and content concerns. This is especially beneficial as sites grow larger and more unweildly over time, and allows both (UX/UI) designers and (content) developers to focus on what is most important to them.

## Features

The primary focus of Whistlepost is the separation of UI and content. Whistlepost doesn't impose restrictions on how the UI is built,
nor does it dictate how and where content is managed.

The key features of Whistlepost include:

* Support for multiple [template engines] including JSP, Thymeleaf and HTL (Sightly)
* Multiple [content management] solutions including NoSQL (e.g. MongoDB), AWS S3, Filesystem, etc.
* Extensions provide additional functionality to support additional site integrations (e.g. OpenGraph, json-ld, etc.)
* Built for containerisation (i.e. Docker) with support for horizontal scaling and multi-tenant solutions.


## Getting Started

### Prerequisites

The only requirements to deploy and test a Whistlepost site locally are [Docker] and a good text editor.

### Creating a new site

1. *Create a project.* Use the yeoman site generator to create a default directory structure
2. *Run Docker.* Use docker-compose to deploy and test locally
3. *Develop the UI.* Modify and test static page fragments for UI and layout adjustments
4. *Author site content.* Define content and add template tags to render dynamic page elements

### Migrating a static site

1. Create directories for both application (ie. HTML, CSS, JavaScript) and content.
2. Create a Whistlepost `docker-compose.yml` file
3. Deploy locally and adjust static content to render correctly (eg. Prefix absolute paths with "/apps/")
4. Define content and add template tags to render dynamic content

### Migrating an Adobe AEM/Sling site

1. Define a `Dockerfile` that extends from the Whistlepost image to load additional required bundles
1. Define a `docker-compose.yml` file with volumes for app and content directories
2. Deploy locally and adjust content to render correctly
	  
### Migrating a Jekyll site

1. Create directories for both application (ie. HTML, CSS, JavaScript) and content.
2. Relocate Jekyll HTML and static assets to the application directory
3. Refactor Jekyll config files as JSON files in the content directory
4. Create a Whistlepost `docker-compose.yml` file
5. Deploy locally and adjust static content to render correctly (eg. Prefix absolute paths with "/apps/")
6. Replace Jekyll Liquid template tags with your preferred template tags

## Extensions

Whistlepost Extensions provide libraries of reusable code that make it simple to integrate your site with
third-party libraries and services.

### Analytics

The analytics extension provides support for adding analytics integration to your site. 

1. To add support for [Google Analytics] sign up to obtain a [tracking ID](https://support.google.com/analytics/answer/7372977?hl=en) (e.g. UA-XXXXXX-XX)

1. Add your tracking ID to the `gradle.properties` configuration file

		gaTrackingCode=UA-XXXXXX-XX

1. Include the analytics content node in each page you want to track. This will render the appropriate
 [tracking code](https://support.google.com/analytics/answer/6086097?hl=en) to include analytics on your page.
 
	A common approach is to add the node in a shared footer page fragment that is included in all pages
(as demonstrated in the file `<projectId-app>/src/main/resources/SLING-INF/content/footer/html.esp`)

		<% sling.include("analytics.google.html"); %>

### Comments

The comments extension provides integration with well-known commenting solutions for web sites.

1. To add integration with [Disqus] sign up to create a [short name](https://help.disqus.com/customer/portal/articles/466208-what-s-a-shortname-).

1. Add the short name in your `gradle.properties` configuration

		 disqusShortName=myorg-projectId

1. Include the comments content node at the bottom of the page where comments should appear. Ensure the current path
is appended to the request URI

		<% sling.include("comments.disqus.html/" + currentNode.path); %>

### Cross-origin Resource Sharing (CORS) Filter

The CORS filter extension adds HTTP response headers to allow for making AJAX requests to other sites

### Error Handler

The error handler extension overrides the default error handling to provide a more user-friendly response that
also suppresses the details of the underlying technology implementation.

### HTTP Headers

The HTTP header extension supports the addition of configurable HTTP response headers.

### JSON-LD

The JSON-LD extension provides support for rich metadata through the rendering of well-known [JSON-LD] structures.  

### Opengraph

The Opengraph extension provides support for rich metadata through rendering well-known [Opengraph] meta tags.

### Paging

The paging extension adds support for rendering paging for list pages.

### Link Rewriting

The link rewriting extension supports rewriting links in the HTML response to allow for reverse proxying, etc.

### RSS Feeds

The RSS feed extension provides support for including RSS feeds in page content.
		
