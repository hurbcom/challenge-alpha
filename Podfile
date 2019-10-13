platform :ios, '12.1'
project 'Alpha', 'AdHoc' => :release,'AppStore' => :release, 'Development' => :debug

inhibit_all_warnings!
use_frameworks!

target 'Alpha' do
  pod 'SwiftLint', '~> 0.31'

  pod 'SwiftGen', '~> 6.1'
  
  target 'UnitTests' do
    inherit! :complete
  end

  target 'UITests' do
    inherit! :complete
  end
end
