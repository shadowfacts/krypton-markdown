package net.shadowfacts.krypton.markdown

import net.shadowfacts.krypton.Page
import net.shadowfacts.krypton.markdown.util.withExtension
import net.shadowfacts.krypton.pipeline.stage.Stage
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

/**
 * @author shadowfacts
 */
class StageRenderMarkdown: Stage() {

	override val id = "markdown"

	private val parser = Parser.builder().build()
	private val renderer = HtmlRenderer.builder().build()

	override fun apply(page: Page, input: String): String {
		page.output = page.output.withExtension("html")
		return renderer.render(parser.parse(input))
	}

}