import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    
    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("TopHNNews")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadNews()
            })
        }
    }
    
    private func listView() -> AnyView {
        switch viewModel.news {
            case .loading:
                return AnyView(Text("Loading...").multilineTextAlignment(.center))
            case .result(let news):
                return AnyView(List(news) { newsItem in
                    NewsItemRow(newsItem: newsItem)
                })
            case .error(let description):
                return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {

     enum LoadableNews {
        case loading
        case result([NewsItem])
        case error(String)
    }

     class ViewModel: ObservableObject {
        let service: HNService
        @Published var news = LoadableNews.loading

         init(service: HNService) {
            self.service = service
            self.loadNews()
        }

         func loadNews() {
            self.news = .loading
            service.getNews(completionHandler: { news, error in
                if let news = news {
                    self.news = .result(news)
                } else {
                    self.news = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension NewsItem: Identifiable { }
