package net.shadowfacts.krypton.markdown

import net.shadowfacts.krypton.Krypton
import net.shadowfacts.krypton.pipeline.selector.PipelineSelectorExtension
import net.shadowfacts.krypton.pipeline.stage.finalstage.FinalStageOutput
import net.shadowfacts.krypton.util.dependencies.Dependencies
import java.io.File

/**
 * @author shadowfacts
 */
fun main(args: Array<String>) {
	val krypton = Krypton {
		source = File("source")
		output = File("output")
	}
	krypton.createPipeline {
		selector = PipelineSelectorExtension("markdown", "mdown", "md")
		addStage(StageRenderMarkdown(), Dependencies {
		})
		final = FinalStageOutput()
	}
	krypton.generate()
}