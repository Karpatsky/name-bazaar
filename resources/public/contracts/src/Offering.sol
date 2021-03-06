pragma solidity ^0.4.14;

import "ens/AbstractENS.sol";
import "OfferingLibrary.sol";

contract Offering {
    using OfferingLibrary for OfferingLibrary.Offering;

    OfferingLibrary.Offering public offering;

    event onTransfer(address newOwner, uint price, uint datetime);

    function Offering(
        address _offeringRegistry,
        address _ens,
        bytes32 _node,
        string _name,
        address _originalOwner,
        address _emergencyMultisig,
        uint _version,
        uint _price
    ) {
        offering.construct(
            _offeringRegistry,
            _ens,
            _node,
            _name,
            _originalOwner,
            _emergencyMultisig,
            _version,
            _price
        );
    }

    function reclaimOwnership() {
        offering.reclaimOwnership();
    }

    // Security method in case user transfers other name to this contract than it's supposed to be
    function claimOwnership(bytes32 node, address _address) {
        offering.claimOwnership(node, _address);
    }

//    function setOfferingRegistry(address _offeringRegistry) {
//        offering.setOfferingRegistry(_offeringRegistry);
//    }
}
