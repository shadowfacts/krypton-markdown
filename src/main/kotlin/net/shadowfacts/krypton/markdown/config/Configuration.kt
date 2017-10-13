package net.shadowfacts.krypton.markdown.config

import net.shadowfacts.krypton.config.Configuration
import net.shadowfacts.krypton.config.config
import org.commonmark.Extension
import org.commonmark.ext.autolink.AutolinkExtension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension
import org.commonmark.ext.ins.InsExtension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

/**
 * @author shadowfacts
 */
var Configuration.markdown: MarkdownConfig by config({ throw RuntimeException("MarkdownConfig cannot be created from String") }, fallback = ::MarkdownConfig)

class MarkdownConfig {

	var autolink = true
	var strikethrough = true
	var tables = true
	var headingAnchor = true
	var ins = true

	var extraExtensions = mutableListOf<Extension>()

	private val extensions: List<Extension> by lazy {
		ArrayList(extraExtensions).apply {
			if (autolink) add(AutolinkExtension.create())
			if (strikethrough) add(StrikethroughExtension.create())
			if (tables) add(TablesExtension.create())
			if (headingAnchor) add(HeadingAnchorExtension.create())
			if (ins) add(InsExtension.create())
		}
	}

	val commonMark by lazy {
		Parser.builder().run {
			extensions(extensions)
			build()
		} to HtmlRenderer.builder().run {
			extensions(extensions)
			build()
		}
	}

}