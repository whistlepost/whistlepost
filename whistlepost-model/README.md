[Sling Models]: https://sling.apache.org/documentation/bundles/models.html

# Whistlepost Model

This project provides a [Sling Models] collection of Whistlepost site elements.

## Overview

Whistlepost models provide support for additional logic and typing specific to the elements that make up a website.

### Templates

Whistlepost templates define how the model is rendered in the final output. Each template may include multiple
definitions that are applied in different contexts (i.e. via selectors). Within each definition a template is
rendered by including template fragments from the current theme.

### Components

Components are similar to templates, however they are not themeable. A component is used to extend
site functionality by including both visual and non-visual code blocks.


## Components

Whistlepost sites are composed of a collection of structural, layout and non-visible components. Components may be
used to render an entire resource, or could be embedded into another page using a 'sling include' directive.

_NOTE: Components may be included in any Sling resource and are not dependent on the rendering engine used._

Some available components are as follows:

| Name         | Description                                                                 | Resource Type           | Selectors                    |
|--------------|-----------------------------------------------------------------------------|-------------------------|------------------------------|
| Analytics    | Non-visible tracking of page metrics                                        | wp/component/analytics | * google                     |
| Branding     | Logos, images and branding text applied to various site elements            | wp/include/branding     | * header                     |
| Calendar     | Dynamic rendering of shareable calendar events                              | wp/template/calendar   |                              |
| Comments     | In-page widgets supporting user comments                                    | wp/component/comments     | * disqus                     |
| Feed         | Dynamic rendering of site feeds via standard formats (e.g. Atom, RSS, etc.) | wp/template/feed       | * atom                       |
| Legal        | Page elements for rendering site-wide legal and other information           | wp/include/legal        | * footer                     |
| Media        | Widgets supporting embedded media such as video                             | wp/media                | * vimeo * youtube            |
| Navigation   | Support for site navigation elements such as menus, etc.                    | wp/include/nav          | * default * footer * columns |
| Robots       | Dynamically render `robots.txt` for a site                                  | wp/template/robots     |                              |
| Search       | Widget support for site search                                              | wp/component/search              | * google                     |
| Sitemap      | Dynamically render `sitemap.xml` for a site                                 | wp/template/sitemap    |                              |
| Social Media | Widget support for sharing on social media sites                            | wp/social               |                              |

## Model Fragments

Model fragments are similar to embedded components, however are more tightly integrated with the rendering of
the page. You might consider fragments to be akin to rendering functions, whereas embedded components represent
a complete rendered resource.

Fragments are currently only applicable for pages using the Thymeleaf rendering engine, and are categorized
according to the supported Sling Models object type.

### Core Fragments

The following core fragments are included:

| Category   | Description                    | Sling Models                     |
|------------|--------------------------------|----------------------------------|
| Branding   | Render branding elements       | org.whistlepost.model.site.Branding   |
| Layout     | Render configured CSS layouts  | org.whistlepost.model.site.Layout     |
| Navigation | Render nav items such as menus | org.whistlepost.model.site.Navigation |


### Schema.org Fragments

Additional Model Fragments are provided to support [Schema.org] entities:

## Pages

Whistlepost also provides default page configurations for common use cases.

| Category        | Description                                                                        | Sling Models                  |
|-----------------|------------------------------------------------------------------------------------|-------------------------------|
| Page            | Render page metadata                                                               | org.whistlepost.model.Page    |
| Feature         | A feature page includes a list of articles with one specified as a feature article | org.whistlepost.model.Feature |
| Article         | An article including a body of text and images                                     |                               |
| Sorted Articles | A list of article summaries sorted by properties                                   |                               |

