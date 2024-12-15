import SwiftUI
import shared

@main
struct iOSApp: App {

    init(){
    // Chamando o KoinInitializer
        KoinInitializerKt.doInitKoin()

    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}