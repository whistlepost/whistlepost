# Whistlepost Configuration

A Context-aware configuration is a localized configuration for resources in Apache Sling. Whistlepost provides
specific configurations for managing different kinds of site content.

## Overview

Whistlepost provides the following scopes for content configuration:

* Sites - configuration that applies across an entire site, such as analytics, branding, etc.
* Pages - configuration applied to different page types, such as sitemaps, feeds, etc.
* Forms - form control definitions that are used to construct form content
* Extensions - service integration configurations
* Views - configure view types for dynamic content

Note that context-aware configuration is hierarchical and can be defined for
any resource, which means that site configuration can be overriden for any
specific page or hierarchy.


## Site Configuration

Each Whistlepost website or web application includes support for
the configurable features listed below.

### Analytics

Various analytics tools provide the ability to track user engagement
on websites. With this site configuration feature you can select
your preferred analytics provider that is then easily embeddable across
your entire site.

### Comments

User feedback and comments are supported via integration of third-party
tools. You can configure your preferred provider with this site configuration.

### Legal

When publishing websites on the Internet it is recommended to include some
legal protections such as copyright, terms of service, usage license, etc.
This site configuration supports both inline text and links to detailed pages.

### Media

Media assets such as images and video may be potentially hosted externally
to a Whistlepost site. This configuration supports alternative locations for media
content.


## Page Configuration

TBD.

## Form Configuration

TBD.

## View Configuration

TBD.
