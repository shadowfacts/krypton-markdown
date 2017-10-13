package net.shadowfacts.krypton.markdown

import net.shadowfacts.krypton.Page
import net.shadowfacts.krypton.markdown.config.markdown
import net.shadowfacts.krypton.markdown.util.withExtension
import net.shadowfacts.krypton.pipeline.stage.Stage

/**
 * @author shadowfacts
 */
class StageRenderMarkdown: Stage() {

	override val id = "markdown"

	override fun scan(page: Page) {
	}

	override fun apply(page: Page, input: String): String {
		page.output = page.output.withExtension("html")

		val (parser, renderer) = page.krypton.config.markdown.commonMark
		return renderer.render(parser.parse(input))
	}

}