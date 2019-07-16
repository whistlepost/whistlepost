# Whistlepost - Digital content authoring and publishing

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

Whistlepost is a Web content rendering engine similar to [Jekyll]. It is built on [Apache Sling], the same platform  on which [Adobe Experience Manager] is built. [Apache Sling] platform, which applies the mantra that everything is a resource.
The benefit of this approach is that is separates the structure of the content from the structure of the site itself, 
which allows both (UX/UI) designers and (content) developers to focus on what is most important to them.

## Features

The primary focus of Whistlepost is the separation of UI and content. Whistlepost doesn't impose restrictions on how the UI is built,
nor does it dictate how and where content is managed.

The key features of Whistlepost include:

* Support for multiple [template engines] including JSP, Thymeleaf and HTL (Sightly)
* Multiple [content management] solutions including NoSQL (e.g. MongoDB), AWS S3, Filesystem, etc.
* Extensions provide additional functionality to support additional site integrations (e.g. OpenGraph, json-ld, etc.)
* Built for containerisation (i.e. Docker) with support for horizontal scaling and multi-tenant solutions.


## Getting Started

Whistlepost provides a [Lazybones] project template that is probably the simplest way to create a new Whistlepost site.
The following steps outline how to use it:

1. Install [Lazybones] via [SDKMAN]:

		$ curl -s "https://get.sdkman.io" | bash
	
		$ source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install lazybones

1. Include the Whistlepost repository in configuration:

		$ lazybones set bintrayRepositories = [micronode/whistlepost, pledbrook/lazybones-templates]

1. Create a new site skeleton using the Whistlepost template:

		$ lazybones create whistlepost-site <site directory>

1. Build and run your new site in Docker:

		$ ./gradlew buildDocker && docker run --rm -p 8080:8080 <projectId>

1. Open site in browser: `http://localhost:8080/<projectId>`
	  

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
		
