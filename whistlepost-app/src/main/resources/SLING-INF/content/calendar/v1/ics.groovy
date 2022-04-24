import net.fortuna.ical4j.model.ContentBuilder

def calendar = resource.adaptTo(org.whistlepost.model.Calendar)

out.write new ContentBuilder().calendar {
    name(calendar.title)

    calendar.events.each { event ->
        vevent {
            summary(event.summary)
        }
    }
}
