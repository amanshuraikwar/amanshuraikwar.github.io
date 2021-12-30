import SwiftUI
import shared

struct ContentView: View {
    @StateObject var viewModel = ViewModel()
    
    var body: some View {
        HomePageView(screenState: $viewModel.screenState)
            .onAppear {
                Task.init {
                    //do {
                        await viewModel.getPortfolioData()
                    //}
                }
            }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
	}
}
